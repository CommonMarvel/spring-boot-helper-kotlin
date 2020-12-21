package common.marvel.helper.extension

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

data class Foo(
    val name: String,
    val age: Int
)

@SpringBootTest
class JsonExtensionsTests {

    @Test
    fun `should jsonStringToObject proper`() {
        Foo(name = "foo", age = 20)
            .toJsonString()
            .jsonStringToObject(Foo::class.java)
            .age shouldBe 20
    }
}
