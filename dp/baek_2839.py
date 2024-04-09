"""
설탕 배달
@link: https://www.acmicpc.net/problem/2839
@date: 2024-04-08
"""


def solve(n, dp):
    if n > 5 and dp[n - 5] != -1:
        dp[n] = dp[n - 5] + 1
        return

    if dp[n - 3] != -1:
        dp[n] = dp[n - 3] + 1
        return

    dp[n] = -1


N = int(input())

input_max = 5001
input_min = 3

if N < input_min or N > input_max:
    raise Exception("Invalid Input")

arr = [0] * (input_max + 1)
arr[1], arr[2], arr[3], arr[4], arr[5] = -1, -1, 1, -1, 1
for index in range(6, N + 1):
    solve(index, arr)

print(arr[N])
