package gigaherz.jsonthings.util.parse.function;

import gigaherz.jsonthings.util.parse.value.ArrayValue;

import java.util.function.Function;

@FunctionalInterface
public interface ArrayValueFunction<T> extends Function<ArrayValue, T>
{
}
