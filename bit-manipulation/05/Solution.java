class Solution {

    private static final int MASK = 0xffffffff;

    public int getSum(int a, int b) {
        int _sum = a ^ b;
        int _carry = ((a & b) << 1);
        while (_carry != 0) {
            int next_sum = _sum ^ _carry;
            int next_carry = ((_sum & _carry) << 1);
            _sum = next_sum;
            _carry = next_carry;
        }
        return _sum;
    }
}