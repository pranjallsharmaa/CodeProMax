# Idea behind the solution

I used Greedy approach for this. There are solutions which utilize Max Heap and Queue as well, however, for some reason, I find the greedy approach more comfortable.

The idea is, take the task which needs to execute the _maximum number of times_ first. Then, based on the value of n, find out how many potential "idle" slots we will have. Later,
we will fill up these idle slots by other tasks

Let's say the task array is something like this: [A,A,A,B,B,C,C] and n = 2
The maximum frequency task is "A", frequency array will look something like [3,2,2,0...]. So, intuitively, this is how I will arrange the execution process

A _ _ A _ _ A

As n = 2, I have to wait for 2 execution time units before I can execute A again
I get a total of 4 idle slots. Now, I will attempt to fill these slots with B

A B _ A B _ A

Finally, the remaining in C

A B C A B C

The final answer is 6. Since we need exactly 6 time units to complete all task execution

Now let's see how we will code what we have done one by one

1. Getting the maximum frequency
	* We can maintain an array of size 26 and increment the corresponding index
	* This is typical frequency array implementation for alphabetical characters
	* We will have to get the maximum from our frequency array freq, by using `Arrays.sort(freq)` and then getting the last element - `freq[25]`
	
2. Finding out idle slots after getting the max value
	* To get idle slots, we will use the formula `int idleSlots = (maxFreq - 1) * n`
	* We do maxFreq - 1, because we cannot fill anything **after** the last occurence of task A. We need to find the idle slots in between the A executions, not after
	* For our example, we get the value as 4 from the above formula
	
3. Fill up these idle slots with remaining tasks
	* Iterate over the remainder of the frequency array in the opposite direction, from index 24
	* To "fill up slots", it means, we will reduce the number of available slots.
	* We will use `idleSlots -= Math.min(freq[i], maxFreq - 1)`

4. Get final answer
	* When the iteration is over, we will have the remaining idleSlots
	* If idleSlots > 0, we will add that to the length of our tasks, else, we will simply return the length of our tasks array
	

## Important Questions/doubts I had with this approach

1. Why should I use `idleSlots -= Math.min(freq[i], maxFreq - 1)` to reduce the slots count, why can't I simply use `idleSlots -= freq[i]`?
	* This is to handle the case where the largest and the second largest element in the frequency array are same, let's take an example and understand
	* tasks = [A,A,A,B,B,B], so frequency array will look something like this - [3,3,0...]
	* maxFreq = 3
	* idleSlots = (3 - 1) * 2 = 4
	* The task execution will look something like this - A _ _ A _ _ A
	* If we now attempt to fill B, the only way we can do that in the idle slots is - A B _ A B _ A
	* This means, we only use 2 idle slots, if we don't use Math.min approach and directly subtract frequency of B, we are saying we will use 3 slots, which is incorrect
	
2. Why final answer is tasks.length OR idleSlots + task.length
	* In best case, let's say there will be **NO** idleSlots, that will mean, no matter the execution order, all tasks executed one after the other with no breaks or idle time
	* Since order doesn't matter, this means the time taken will be tasks.length, meaning, all tasks executed without breaks, so time = number of tasks, simply
	* If we do have idleSlots, we will simply add that _"extra"_ time we took to execute the process
	
3. Why do I need to check if idleSlots > 0
	* We need to check this, in case, we run out of idle slots and we still have tasks to be executed. Take an example of tasks = [A,A,B,B,C,C,D] and n = 1
	* Here, maxFreq = 2 and idleSlots = (2 - 1) * 1 = 1
	* Initial task execution will look like - A _ A
	* We have ONLY 1 idle slot and we still have 6 tasks to be executed. It means, the idle slot will only be filled by 1 task, and the remaining will be executed **AFTER** the last A execution
	* We can also modify the code, by decrementing idleSlots only if it is greater than or equal to 0 and then simply returning `(idleSlots > 0 ? idleSlots : 0) + tasks.length`, but that will mean the same thing in extra steps, so no need for that
	
## Complexity

- **Time - O(n)**: We need to iterate the tasks array once to create the frequency array
			 Sorting the frequency array will be constant (as size is always 26)
			 Iterating over the frequency array to decrement the idleSlots count will also be constant, (as size is always 26)\
- **Space - O(1)**: We are using another frequency array, but the size of that will always be constant (26)
