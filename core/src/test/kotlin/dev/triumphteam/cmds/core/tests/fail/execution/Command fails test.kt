/**
 * MIT License
 *
 * Copyright (c) 2019-2021 Matt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.triumphteam.cmds.core.tests.fail.execution

import dev.triumphteam.cmds.core.cases.execution.DefaultSubCommandNoArgs
import dev.triumphteam.cmds.core.implementation.ExecutionResult
import dev.triumphteam.cmds.core.implementation.TestCommandManager
import dev.triumphteam.cmds.core.implementation.TestSender
import dev.triumphteam.cmds.core.implementation.toArgs
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@Suppress("ClassName")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class `Command fails test` {

    private val commandManager = TestCommandManager()

    @Test
    fun `Unknown command fail`() {
        commandManager.registerCommand(DefaultSubCommandNoArgs())
        val sender = TestSender()
        commandManager.execute(sender, "wrong", "".toArgs())

        Assertions.assertThat(sender.result).isEqualTo(ExecutionResult.UNKNOWN_COMMAND)
    }

}