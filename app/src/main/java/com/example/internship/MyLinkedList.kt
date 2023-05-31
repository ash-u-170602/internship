package com.example.internship

class MyLinkedList<T> {
    private class Node<T>(val value: T) {
        var next: Node<T>? = null
    }

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var count: Int = 0

    fun add(item: T) {
        val newNode = Node(item)

        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        count++

    }

    fun size(): Int {
        return count
    }


    fun get(index: Int): T? {
        if (index < 0 || index >= count) {
            return null
        }

        var currentNode = head
        for (i in 0 until index) {
            currentNode = currentNode?.next
        }

        return currentNode?.value
    }
}
