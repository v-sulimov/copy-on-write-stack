package com.vsulimov.stack

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CopyOnWriteStackTest {

    @Test
    fun emptyConstructor() {
        val stack = CopyOnWriteStack<Int>()
        assertEquals(expected = 0, actual = stack.size())
        assertTrue(stack.isEmpty())
    }

    @Test
    fun constructorWithElements() {
        val stack = CopyOnWriteStack(listOf(1, 2, 3, 4))
        assertEquals(expected = 4, actual = stack.size())
        assertFalse(stack.isEmpty())
    }

    @Test
    fun push() {
        val stack = CopyOnWriteStack<Int>()
        val newStack = stack.push(1).push(2)
        assertEquals(expected = 2, actual = newStack.size())
    }

    @Test
    fun pop() {
        val stack = CopyOnWriteStack<Int>().push(1).push(2)
        val (element, newStack) = stack.pop()
        assertEquals(expected = 2, actual = element)
        assertEquals(expected = 1, actual = newStack.size())
        assertEquals(expected = 1, actual = newStack.peek())
    }

    @Test
    fun popFromEmpty() {
        val stack = CopyOnWriteStack<Int>()
        val (element, newStack) = stack.pop()
        assertNull(element)
        assertEquals(expected = 0, actual = newStack.size())
    }

    @Test
    fun peek() {
        val stack = CopyOnWriteStack<Int>().push(1).push(2)
        assertEquals(expected = 2, actual = stack.peek())
    }

    @Test
    fun peekEmpty() {
        val stack = CopyOnWriteStack<Int>()
        assertNull(stack.peek())
    }

    @Test
    fun size() {
        val stack = CopyOnWriteStack<Int>()
        assertEquals(expected = 0, actual = stack.size())
        val newStack = stack.push(1)
        assertEquals(expected = 1, actual = newStack.size())
    }

    @Test
    fun isEmpty() {
        val stack = CopyOnWriteStack<Int>()
        assertTrue(stack.isEmpty())
        val newStack = stack.push(1)
        assertFalse(newStack.isEmpty())
    }

    @Test
    fun testMapInPlace() {
        val stack = CopyOnWriteStack<Int>().push(1).push(2).push(3)
        val mappedStack = stack.mapInPlace { it * 2 }

        assertEquals(expected = 3, actual = mappedStack.size())

        val (element1, stackAfterPop1) = mappedStack.pop()
        assertEquals(expected = 6, actual = element1)

        val (element2, stackAfterPop2) = stackAfterPop1.pop()
        assertEquals(expected = 4, actual = element2)

        val (element3, stackAfterPop3) = stackAfterPop2.pop()
        assertEquals(expected = 2, actual = element3)

        assertTrue(stackAfterPop3.isEmpty())
    }

    @Test
    fun testEquals() {
        val firstStack = CopyOnWriteStack<Int>().push(1).push(2).push(3)
        val secondStack = CopyOnWriteStack<Int>().push(1).push(2).push(3)
        val thirdStack = CopyOnWriteStack<Int>().push(1).push(2)

        assertTrue(firstStack == secondStack)
        assertFalse(secondStack == thirdStack)
    }
}
