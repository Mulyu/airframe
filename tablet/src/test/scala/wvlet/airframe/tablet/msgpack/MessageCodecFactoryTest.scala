/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wvlet.airframe.tablet.msgpack

import org.msgpack.core.{MessagePack, MessagePacker}
import wvlet.airframe.AirframeSpec
import wvlet.airframe.tablet.Schema
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import wvlet.airframe.tablet.Schema.ColumnType

/**
  *
  */
class MessageCodecFactoryTest extends AirframeSpec {

  def roundtrip[A](codec: MessageCodec[A], v: A, expectedType: ColumnType): MessageHolder = {
    val h = new MessageHolder
    debug(s"Testing roundtrip: ${v}")
    val packer = MessagePack.newDefaultBufferPacker()
    codec.pack(packer, v)
    val unpacker = MessagePack.newDefaultUnpacker(packer.toByteArray)
    codec.unpack(unpacker, h)

    h.isNull shouldBe false
    h.getValueType shouldBe expectedType
    h.getLastValue shouldBe v
    h
  }

  "MessageCodecFactory" should {
    "support int" in {
      val codec = MessageCodec.of[Int]
      forAll { (v: Int) =>
        roundtrip(codec, v, Schema.INTEGER)
      }
    }

    "support long" in {
      val codec = MessageCodec.of[Long]
      forAll { (v: Long) =>
        roundtrip(codec, v, Schema.INTEGER)
      }
    }

    "support boolean" in {
      val codec = MessageCodec.of[Boolean]
      forAll { (v: Boolean) =>
        roundtrip(codec, v, Schema.BOOLEAN)
      }
    }

    "support short" in {
      val codec = MessageCodec.of[Short]
      forAll { (v: Short) =>
        roundtrip(codec, v, Schema.INTEGER)
      }
    }

    "support byte" in {
      val codec = MessageCodec.of[Byte]
      forAll { (v: Byte) =>
        roundtrip(codec, v, Schema.INTEGER)
      }
    }

    "support float" in {
      val codec = MessageCodec.of[Float]
      forAll { (v: Float) =>
        roundtrip(codec, v, Schema.FLOAT)
      }
    }

    "support double" in {
      val codec = MessageCodec.of[Double]
      forAll { (v: Double) =>
        roundtrip(codec, v, Schema.FLOAT)
      }
    }

    "support string" in {
      val codec = MessageCodec.of[String]
      forAll { (v: String) =>
        roundtrip(codec, v, Schema.STRING)
      }
    }

    "support primitive int array" in {
      val codec = MessageCodec.of[Array[Int]]
      forAll { (v: Array[Int]) =>
        roundtrip(codec, v, Schema.ANY)
      }
    }

    "support primitive string array" in {
      val codec = MessageCodec.of[Array[String]]
      forAll { (v: Array[String]) =>
        roundtrip(codec, v, Schema.ANY)
      }
    }

    "support primitive float array" in {
      val codec = MessageCodec.of[Array[Float]]
      forAll { (v: Array[Float]) =>
        roundtrip(codec, v, Schema.ANY)
      }
    }

    "support primitive double array" in {
      val codec = MessageCodec.of[Array[Double]]
      forAll { (v: Array[Double]) =>
        roundtrip(codec, v, Schema.ANY)
      }
    }

    "support primitive boolean array" in {
      val codec = MessageCodec.of[Array[Boolean]]
      forAll { (v: Array[Boolean]) =>
        roundtrip(codec, v, Schema.ANY)
      }
    }

    "support primitive long array" in {
      val codec = MessageCodec.of[Array[Long]]
      forAll { (v: Array[Long]) =>
        roundtrip(codec, v, Schema.ANY)
      }
    }

    "support primitive short array" in {
      val codec = MessageCodec.of[Array[Short]]
      forAll { (v: Array[Short]) =>
        roundtrip(codec, v, Schema.ANY)
      }
    }
  }

}
