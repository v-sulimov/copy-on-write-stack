package com.vsulimov.stack

import org.junit.Before
import org.junit.Test

internal class CopyOnWriteStackTest {

    private lateinit var stack: CopyOnWriteStack<Int>
    private var noSuchElementExceptionCount = 0

    @Before
    fun setUp() {
        stack = CopyOnWriteStack()
        noSuchElementExceptionCount = 0
    }

    @Test
    fun push() {
        val element = 0
        stack = stack.push(element)
        assert(!stack.isEmpty())
        assert(stack.peek() == element)
    }

    @Test
    fun pop() {
        val firstElement = 0
        val secondElement = 1
        stack = stack.push(firstElement)
        stack = stack.push(secondElement)
        stack = stack.pop()
        assert(!stack.isEmpty())
        assert(stack.peek() == firstElement)
    }

    @Test
    fun peek() {
        val firstElement = 0
        val secondElement = 1
        stack = stack.push(firstElement)
        stack = stack.push(secondElement)
        assert(!stack.isEmpty())
        assert(stack.peek() == secondElement)
    }

    @Test
    fun peekIf() {
        val firstElement = 0
        val secondElement = 1
        stack = stack.push(firstElement)
        stack = stack.push(secondElement)
        assert(stack.peekIf { it == firstElement } == firstElement)
        assert(stack.peekIf { it == secondElement } == secondElement)
        assert(stack.peekIf { it == 3 } == null)
    }

    @Test
    fun reduceEach() {
        val firstElement = 0
        val secondElement = 1
        stack = stack.push(firstElement)
        stack = stack.push(secondElement)
        stack = stack.reduceEach { if (it == 0) 2 else it }
        stack = stack.reduceEach { if (it == 1) 4 else it }
        assert(stack.peek() == 4)
        stack = stack.pop()
        assert(stack.peek() == 2)
    }

    @Test
    fun replaceAllWith() {
        stack = stack.push(0)
        stack = stack.push(1)
        assert(stack.size() == 2)
        stack = stack.replaceAllWith(3)
        assert(stack.size() == 1)
        assert(stack.peek() == 3)
    }

    @Test
    fun size() {
        val firstElement = 0
        val secondElement = 1
        assert(stack.size() == 0)
        stack = stack.push(firstElement)
        assert(stack.size() == 1)
        stack = stack.push(secondElement)
        assert(stack.size() == 2)
        stack = stack.pop()
        assert(stack.size() == 1)
        stack = stack.pop()
        assert(stack.size() == 0)
    }

    @Test
    fun isEmpty() {
        val element = 0
        assert(stack.isEmpty())
        stack = stack.push(element)
        assert(!stack.isEmpty())
        stack = stack.pop()
        assert(stack.isEmpty())
    }

    @Test
    fun peekWhenEmpty() {
        try {
            stack.peek()
        } catch (exception: NoSuchElementException) {
            noSuchElementExceptionCount++
        }
        assert(noSuchElementExceptionCount == 1)
    }

    @Test
    fun popWhenEmpty() {
        try {
            stack.pop()
        } catch (exception: NoSuchElementException) {
            noSuchElementExceptionCount++
        }
        assert(noSuchElementExceptionCount == 1)
    }

    @Test
    fun equals() {
        stack = stack.push(0)
        stack = stack.push(1)

        var otherStack = CopyOnWriteStack<Int>()
        otherStack = otherStack.push(0)
        otherStack = otherStack.push(1)

        assert(stack == otherStack)
    }
}
