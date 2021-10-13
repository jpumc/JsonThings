package dev.gigaherz.jsonthings.codegen.codetree.expr.impl;

import dev.gigaherz.jsonthings.codegen.codetree.expr.CodeBlockInternal;
import dev.gigaherz.jsonthings.codegen.codetree.expr.Expr;

public abstract class ExprBase<R> implements Expr<R>
{
    protected final CodeBlockInternal<R, ?, ?> cb;

    public ExprBase(CodeBlockInternal<R, ?, ?> cb)
    {
        this.cb = cb;
    }

    public CodeBlockInternal<R, ?, ?> block()
    {
        return cb;
    }
}
