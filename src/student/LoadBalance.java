package student;
/*
 * This LoadBalance class is meant to contain your algorithm.
 * You should implement the static method:
 *   loadBalance - which finds the best partitioning of the tasks
 *                 among the processors
 */


import java.util.ArrayList;
import java.util.List;

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
        List<List<Integer>> foobar_partitions = partition(tasks, procs);
        System.out.println(foobar_partitions.toString());
        System.out.println(maxWorkloadIndex(foobar_partitions));
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
    private static int workload(List<Integer> values) {
        int workload = 0;
        for (int value : values) {
            workload += value;
        }
        return workload;
    }
    private static int maxWorkloadIndex(List<List<Integer>> partitions) {
        int workload = 0;
        int workload_index = -1;

        for(int i = 0; i < partitions.size(); i++) {
            List<Integer> partition = partitions.get(i);
            int curr_workload = workload(partition);
            if (curr_workload > workload) {
                workload = curr_workload;
                workload_index = i;
            }
        }

        return workload_index;
    }
    private static List<List<Integer>> partition(int[] input_list, int parts) {
        int partition_size = (int)Math.ceil(input_list.length / (double)parts);
        List<List<Integer>> partitions = new ArrayList<>();
        for (int i = 0; i < parts; i++) {
            List<Integer> curr_partition = new ArrayList<>();
            for (int j = i*partition_size; j < i*partition_size + Math.min(partition_size, input_list.length - i*partition_size); j++) {
                curr_partition.add(input_list[j]);
            }
            partitions.add(curr_partition);
        }

        return partitions;
    }
}

