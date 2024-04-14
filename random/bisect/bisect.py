import unittest

def bisect_left(li, x):
    lo = 0
    hi = len(li)
    while lo < hi:
        mid = (lo + hi) // 2
        if li[mid] < x:
            lo = mid + 1
        else:
            hi = mid
    return lo

def bisect_right(li, x):
    lo = 0
    hi = len(li)
    while lo < hi:
        mid = (lo + hi) // 2
        if li[mid] > x:
            hi = mid
        else:
            lo = mid + 1
    return lo

class TestBinarySearch(unittest.TestCase):

    def test_bisect_left(self):
        self.assertEqual(bisect_left([], 1), 0)
        self.assertEqual(bisect_left([1], 1), 0)
        self.assertEqual(bisect_left([1, 2], 1), 0)
        self.assertEqual(bisect_left([1, 2], 2), 1)
        self.assertEqual(bisect_left([2, 2, 2, 2, 2], 2), 0)
        self.assertEqual(bisect_left([1, 2, 3, 5, 6], 4), 3)
        self.assertEqual(bisect_left([1, 2, 3, 3, 3, 4, 5], 3), 2)

    def test_bisect_right(self):
        self.assertEqual(bisect_right([], 1), 0)
        self.assertEqual(bisect_right([1], 1), 1)
        self.assertEqual(bisect_right([1, 2], 1), 1)
        self.assertEqual(bisect_right([1, 2], 2), 2)
        self.assertEqual(bisect_right([2, 2, 2, 2, 2], 2), 5)
        self.assertEqual(bisect_right([1, 2, 3, 5, 6], 4), 3)
        self.assertEqual(bisect_right([1, 2, 3, 3, 3, 4, 5], 3), 5)

if __name__ == '__main__':
    unittest.main()
