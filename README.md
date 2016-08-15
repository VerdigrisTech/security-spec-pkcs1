# spec-pkcs1

Scala library with PKCS1EncodedKeySpec class which represents PKCS#1 encoded key.

## Prerequisites

* Scala 2.10+
* sbt 0.13+

## Specifying Dependency

If you want to use this library in your SBT project, just add these lines to your `build.sbt` file:

```sbt
libraryDependencies += "co.verdigris.security.specs" % "spec-pkcs1" % "0.1.0"

resolvers += "Verdigris Security Specs" at "https://verdigristech.github.io/spec-pkcs1/"
```

## Usage

To generate an instance of [PrivateKey](https://docs.oracle.com/javase/8/docs/api/java/security/PrivateKey.html),
instantiate `PKCS1EncodedKeySpec` with a byte array representation of PKCS#1 encoding and pass it to `generatePrivate`
method of a [KeyFactory](https://docs.oracle.com/javase/8/docs/api/java/security/KeyFactory.html) instance.

```scala
val pemStr = scala.io.Source.fromFile("privateKey.pem")
val pemByteArray = Base64.decodeBase64(
  pemStr.stripPrefix("-----BEGIN RSA PRIVATE KEY-----")
        .stripSuffix("-----END RSA PRIVATE KEY-----"))
val kf = KeyFactory.getInstance("RSA")
val keySpec = new PKCS1EncodedKeySpec(pemByteArray)
val privateKey = kf.generatePrivate(keySpec)
```

## Building

To build this library from source manually, just run:

```bash
$ sbt package
```

The JAR file should be generated at `./target/scala-2.1X/security-specs-pkcs1_2.10-0.1.0.jar`.

## License

Distributed under Apache 2.0 license. See [LICENSE.md](https://github.com/VerdigrisTech/security-spec-pkcs1/blob/master/LICENSE.md)
for more information.
