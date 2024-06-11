import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character top;
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                case '{':
                case '[':
                    stk.push(ch);
                    break;
                case ')':
                    top = pop(stk);
                    if (top == null || top != '(') {
                        return false;
                    }
                    break;
                case '}':
                    top = pop(stk);
                    if (top == null || top != '{') {
                        return false;
                    }
                    break;
                case ']':
                    top = pop(stk);
                    if (top == null || top != '[') {
                        return false;
                    }
                    break;
            }
        }
        if (stk.isEmpty()) {
            return true;
        }
        return false;
    }

    private Character pop(Stack<Character> stk) {
        if (stk.isEmpty()) {
            return null;
        }
        return stk.pop();
    }
}