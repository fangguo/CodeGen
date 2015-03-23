/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.base;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.ORDERED;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Emil Forslund
 */
public class DefaultRenderStack implements RenderStack {
    
    private final Deque<Object> stack;
    
    public DefaultRenderStack() {
        stack = new ArrayDeque<>();
    }
    
    public void push(Object obj) {
        stack.push(obj);
    }
    
    public Object pop() {
        return stack.pop();
    }

    @Override
    public <T> Stream<T> fromBottom(Class<T> type) {
        return all(type, stack.descendingIterator());
    }

    @Override
    public <T> Stream<T> fromTop(Class<T> type) {
        return all(type, stack.iterator());
    }
    
    private <T> Stream<T> all(Class<T> type, Iterator<Object> i) {
        final Iterable<Object> it = () -> i;
        return StreamSupport.stream(it.spliterator(), false)
            .filter(o -> type.isAssignableFrom(o.getClass()))
            .map(o -> (T) o);
    }
}
