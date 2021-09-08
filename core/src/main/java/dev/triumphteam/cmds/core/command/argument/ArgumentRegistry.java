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
package dev.triumphteam.cmds.core.command.argument;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import dev.triumphteam.cmds.core.exceptions.SubCommandRegistrationException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class ArgumentRegistry<S> {

    private final Map<Class<?>, ArgumentResolver<S>> arguments = new HashMap<>();

    @SuppressWarnings("UnstableApiUsage")
    public ArgumentRegistry() {
        register(int.class, (sender, arg) -> Ints.tryParse(arg));
        register(Integer.class, (sender, arg) -> Ints.tryParse(arg));

        register(long.class, (sender, arg) -> Longs.tryParse(arg));
        register(Long.class, (sender, arg) -> Longs.tryParse(arg));

        register(float.class, (sender, arg) -> Floats.tryParse(arg));
        register(Float.class, (sender, arg) -> Floats.tryParse(arg));

        register(double.class, (sender, arg) -> Doubles.tryParse(arg));
        register(Double.class, (sender, arg) -> Doubles.tryParse(arg));

        register(Boolean.class, (sender, arg) -> Boolean.valueOf(arg));
        register(boolean.class, (sender, arg) -> Boolean.valueOf(arg));

        register(String.class, (sender, arg) -> arg);
    }

    public void register(@NotNull final Class<?> clazz, final ArgumentResolver<S> argument) {
        arguments.put(clazz, argument);
    }

    // FIXME: 9/2/2021 invert it
    public boolean isRegisteredType(@NotNull final Class<?> clazz) {
        return arguments.get(clazz) != null;
    }

    @NotNull
    public ArgumentResolver<S> getResolver(@NotNull final Class<?> clazz) {
        final ArgumentResolver<S> resolver = arguments.get(clazz);

        // Should never throw, but, just in case
        if (resolver == null) {
            throw new SubCommandRegistrationException("Type \"" + clazz.getName() + "\" is not registered!");
        }

        return resolver;
    }

}