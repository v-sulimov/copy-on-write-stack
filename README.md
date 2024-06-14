# Copy-on-write stack
The Copy-on-write stack represents a last-in-first-out (LIFO) stack of objects.

The usual push and pop operations are provided, as well as a method to peek at the top item on the stack and
a method to test for whether the stack is empty.

## Create Stack
```kotlin
val stack = CopyOnWriteStack<String>()
```

## Push
```kotlin
stack = stack.push("Hello, World!")
```

## Pop
```kotlin
stack = stack.pop()
```

## Peek
```kotlin
val element = stack.peek()
```

## Peek if
```kotlin
val element = stack.peekIf { it == 0 }
```

## Size
```kotlin
val size = stack.size()
```

## Reduce
```kotlin
stack = stack.reduceEach { if (it == 0) 1 else it }
```

## Replace all with
```kotlin
stack = stack.replaceAllWith(0)
```

## License
<pre>
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
