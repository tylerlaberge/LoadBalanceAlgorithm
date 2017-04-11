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
    public static int loadBalance(int processes, int[] tasks, int[] partitions)
    {
        int workload_bound = sum(tasks)/processes;
        int[] bounded_partition = boundedPartition(tasks, processes, workload_bound);
        while (bounded_partition != null) {
            workload_bound = max(bounded_partition);
            bounded_partition = boundedPartition(tasks, processes, workload_bound);
        }
        while (bounded_partition == null) {
            bounded_partition = boundedPartition(tasks, processes, workload_bound++);
        }
        System.arraycopy(bounded_partition, 0, partitions, 0, bounded_partition.length);
        return max(bounded_partition);
    }

    private static int[] boundedPartition(int[] jobs, int processes, int bound) {
        int[] partitions = new int[processes];
        int partitioned_elements = 0;
        for (int i = 0; i < processes; i++){
            for (int j = partitioned_elements; j < jobs.length; j++) {
                int curr_job = jobs[j];
                if (partitions[i] + curr_job < bound) {
                    partitions[i] += curr_job;
                    partitioned_elements += 1;
                }
                else {
                    break;
                }
            }
        }
        if (partitioned_elements < jobs.length) {
            return null;
        }
        else {
            return partitions;
        }
    }

    private static int sum(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    private static int max(int[] values) {
        int curr_max = 0;
        for (int value : values) {
            if (value > curr_max) {
                curr_max = value;
            }
        }
        return curr_max;
    }
 }

