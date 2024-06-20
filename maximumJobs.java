
import java.util.Arrays;

public class maximumJobs {

    public static void main(String[] args)
    {
        int[] duration = {30, 15, 40, 80};
        int[] deadline = {100, 90, 110, 80};

//        int[] duration = {20, 30, 15};
//        int[] deadline = {20, 30, 90};

        System.out.println(maxJobs(duration, deadline));
    }

    public static int maxJobs(int[] duration, int[] deadline)
    {
        int[][] jobs = new int[duration.length][2];
        int noOfJobs, timeReq = 0;
        TreeNode root = new TreeNode(0);

        for(int i = 0; i<duration.length; i++)
        {
            jobs[i][0] = deadline[i];
            jobs[i][1] = duration[i];
        }

        // it is imp. to sort the array based on deadline, cause if we take up a job with small deadline
        // that can be completed if taken first, but we did not sort the array to do so, then we will
        // miss that job bec we took another job with higher duration earlier
        //eg      int[] duration = {30, 15, 40, 80};
        //        int[] deadline = {100, 30, 110, 80};
        // unsorted job2 can't be completed as we will either take first job/not so we will do
        // 30 + 15 <= 30 / we will do 0 + 15 <= 30 so we get output 2
        // if we do this we miss 1st/2nd job when in reality both can be completed if its sorted
        //        int[] duration = {15, 80, 30, 40};
        //        int[] deadline = {30, 80, 100, 110};
        // we will do 1st -> 3rd -> 4th
        Arrays.sort(jobs, java.util.Comparator.comparingInt(a->a[0]));

        makeTree(root, timeReq, jobs, 0);

        noOfJobs = countJobs(root);

        return noOfJobs;
    }

    public static void makeTree(TreeNode root, int timeReq, int[][] jobs, int idx)
    {
        if(idx >= jobs.length)
            return ;

        if(timeReq + jobs[idx][1] <= jobs[idx][0])
        {
            root.left = new TreeNode(timeReq + jobs[idx][1]);
            root.right = new TreeNode(timeReq);
            makeTree(root.left, timeReq + jobs[idx][1], jobs, idx+1);
            makeTree(root.right, timeReq, jobs, idx+1);
        }
        else
        {
            root.left = null;
            root.right = new TreeNode(timeReq);
            makeTree(root.right, timeReq, jobs, idx+1);
        }

    }

    public static int countJobs(TreeNode root)
    {
        if(root == null)
            return 0;
        int right = 0, left = 0;
        if(root.left != null)
            left = 1 + countJobs(root.left);
        if(root.right != null)
            right = countJobs(root.right);

        return Math.max(left, right);
    }
}
