package student;
/*
 * This LoadBalance class is meant to contain your algorithm.
 * You should implement the static method:
 *   loadBalance - which finds the best partitioning of the tasks
 *                 among the processors
 */


public class LoadBalance
{
    // Simple example routine that just sets evenly spaced partitions
    //
    // inputs:
    //   the number of processors
    //   an array of task values
    // outputs:
    //   fill the the partition array with the sums of your partitions (from left to right)
    //   return the amount of work in the largest partition
    //
    // For example if we had 3 processors and these tasks: 1 2 1 2 3 4 1 3 2
    // and if we partitioned with 3 tasks in each partition
    // The partition sums would be: 4, 9, 6  and the return value would be 9
    //
    // In this simple code some tasks will be lost if the number of tasks is not evenly
    // divisible by the number of processors.
    public static int loadBalance(int procs, int[] tasks, int[] partitions)
    {
        int avg = tasks.length / procs;

        // count total work in each partition
        int max = 0;
        int i = 0;
        for (int p = 0; p < procs; p++) {
            int total = 0;
            for (int cnt = 0; cnt < avg; cnt++)
                total += tasks[i++];
            partitions[p] = total;
            if (total > max)
                max = total;
        }

        return max;
    }
}

