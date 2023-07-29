# 11. Container With Most Water

https://leetcode.com/problems/container-with-most-water/

Topics:

-   Two Pointer

Logic:

-   Area of container = width x height
    = (j - i) x min(height(i), height(j))

-   To start with, let's try to maximize the width and see what
    answer we get. The width is fixed.

-   Let's continue to keep the width fixed and look at a width
    of (max - 1) but what we want to find is not the maximum area
    at this size rather at size >= (max - 1). To move towards this
    direction, we want to move the pointer from the smallest of
    the previous two heights. (The reason this works can be better
    explained with diagrams).

-   The rough intuition is this: We want to try and maximize height
    as we iterate towards smaller widths. Because the height part
    of the area is bound by the minimum of a height pairing, we want
    to constantly try and discard the minimum height as we move inwards

-   e.g. [9, 100, 2, 1]
    comparisions:
    a. (9, 1) -> 1 is the min height and we get a width of 3.

    b.1 (100, 1) -> Well, 1 is still the min height but now the width is smaller.
    b.2 (9, 2) -> The height increased by 1 for the same smaller width as above. This is the better move.
