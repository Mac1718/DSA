/*
Problem:
Given a string containing only the characters '(', ')', '{', '}', '[' and ']',
decide whether the brackets are closed in the correct order. For example "()[]{}"
is valid but "(]" is not, and "([)]" is not (they cross).

Approach:
We use a stack. Going left to right, whenever we see an open bracket we push it
onto the stack. When we see a closing bracket, the most recent open bracket must
match it, so we pop from the stack and compare. If it doesn't match, or the stack
is empty, the string is invalid. At the end the stack must be empty too.

Why this works:
Brackets must nest properly, so the last opened bracket is always the first that
must be closed. A stack naturally keeps track of that "last opened, first closed"
order, which is exactly the rule for valid parentheses.

Time Complexity:
O(n) where n is the length of the string, one pass over it.

Space Complexity:
O(n) in the worst case for the stack when all characters are open brackets.
*/

import java.util.Stack;

class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        return stack.isEmpty();
    }
}
