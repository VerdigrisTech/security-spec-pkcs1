/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.verdigris.security.spec

import java.math.BigInteger
import java.security.GeneralSecurityException
import java.security.spec.RSAPrivateCrtKeySpec

import sun.security.util.{DerInputStream, DerValue}

class PKCS1EncodedKeySpec(encodedKey: Array[Byte]) extends RSAPrivateCrtKeySpec(
  RsaByteArraySequence.getModulus(encodedKey),
  RsaByteArraySequence.getPublicExponent(encodedKey),
  RsaByteArraySequence.getPrivateExponent(encodedKey),
  RsaByteArraySequence.getPrimeP(encodedKey),
  RsaByteArraySequence.getPrimeQ(encodedKey),
  RsaByteArraySequence.getPrimeExponentP(encodedKey),
  RsaByteArraySequence.getPrimeExponentQ(encodedKey),
  RsaByteArraySequence.getCrtCoefficient(encodedKey)) {

  def getEncoded: Array[Byte] = this.encodedKey
  def getFormat: String = "PKCS#1"
}

private object RsaByteArraySequence {
  private def getSequence(encodedKey: Array[Byte]): Array[DerValue] = {
    val derInputStream = new DerInputStream(encodedKey)
    val sequence = derInputStream.getSequence(0)

    // If sequence length is less than 9, something is wrong with the input byte array
    if (sequence.length < 9) {
      throw new GeneralSecurityException("Could not parse a PKCS1 private key.")
    }

    sequence
  }

  def getModulus(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(1).getBigInteger
  }

  def getPublicExponent(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(2).getBigInteger
  }

  def getPrivateExponent(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(3).getBigInteger
  }

  def getPrimeP(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(4).getBigInteger
  }

  def getPrimeQ(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(5).getBigInteger
  }

  def getPrimeExponentP(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(6).getBigInteger
  }

  def getPrimeExponentQ(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(7).getBigInteger
  }

  def getCrtCoefficient(encodedKey: Array[Byte]): BigInteger = {
    this.getSequence(encodedKey)(8).getBigInteger
  }
}
