package co.verdigris.security.spec

import java.io.IOException
import java.security.{KeyFactory, PrivateKey}
import java.security.spec.RSAPrivateCrtKeySpec

import org.apache.commons.codec.binary.Base64

import scala.io.Source

class PKCS1EncodedKeySpecSpec extends UnitSpec {
  def validPem = new {
    val file = Source.fromURL(getClass.getResource("/private.key"))
    val asString = try file.mkString finally file.close()
    val asByteArray = Base64.decodeBase64(
      asString.stripPrefix("-----BEGIN RSA PRIVATE KEY-----")
        .stripSuffix("-----END RSA PRIVATE KEY-----")
        .split("\n")
        .map(_.trim.filter(_ >= ' '))
        .mkString
    )
  }

  def invalidPem = new {
    val file = Source.fromURL(getClass.getResource("/invalid.key"))
    val asString = try file.mkString finally file.close()
    val asByteArray = Base64.decodeBase64(
      asString.stripPrefix("-----BEGIN RSA PRIVATE KEY-----")
        .stripSuffix("-----END RSA PRIVATE KEY-----")
        .split("\n")
        .map(_.trim.filter(_ >= ' '))
        .mkString
    )
  }

  "PKCS1EncodedKeySpec" should "extend from RSAPrivateCrtKeySpec" in {
    val pkcs1EncodedKeySpec = new PKCS1EncodedKeySpec(validPem.asByteArray)

    pkcs1EncodedKeySpec shouldBe a [RSAPrivateCrtKeySpec]
  }

  it should "be used by KeyFactory to generate an instance of PrivateKey" in {
    val pkcs1EncodedKeySpec = new PKCS1EncodedKeySpec(validPem.asByteArray)
    val kf = KeyFactory.getInstance("RSA")

    val privateKey = kf.generatePrivate(pkcs1EncodedKeySpec)

    privateKey shouldBe a [PrivateKey]
  }

  it should "throw an IOException when instantiating with invalid PEM byte array" in {
    an [IOException] should be thrownBy new PKCS1EncodedKeySpec(invalidPem.asByteArray)
  }
}
