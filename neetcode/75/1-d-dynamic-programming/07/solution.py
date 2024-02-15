class Solution:
    def coinChange(self, coins: list[int], amount: int) -> int:
        dp = [0]  # 0 coins needed to make amount=0.
        for i in range(1, amount + 1):
            min_subproblem_solution = None
            for coin in coins:
                if i - coin < 0:
                    continue
                subproblem_solution = dp[i - coin]
                if subproblem_solution is None:
                    continue
                if (
                    min_subproblem_solution is None
                    or subproblem_solution < min_subproblem_solution
                ):
                    min_subproblem_solution = subproblem_solution
            if min_subproblem_solution is not None:
                min_subproblem_solution += 1
            dp.append(min_subproblem_solution)
        solution = dp[-1]
        if solution is None:
            return -1
        return solution
