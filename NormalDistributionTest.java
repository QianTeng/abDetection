package algorithms;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.inference.TestUtils;

/**
 * Created by qt on 2015/1/8.
 */
public class NormalDistributionTest {
    private double[] arr;
    private double mean;
    private double stdeviation;
    private final static double CONFIDENCE = 0.01;//作为置信度，判断一个数组是否是正太分布，1-CONFIDENCE表示满足正态分布的概率
    private final static int K = 2;//u + k* 中的k
    private NormalDistribution distribution;
    public NormalDistributionTest(double[] arr){
        this.arr = arr;
        DescriptiveStatistics statistics = new DescriptiveStatistics();
        for (double o : arr) {
            statistics.addValue(o);
        }
        mean = statistics.getMean();
        stdeviation = statistics.getStandardDeviation();
        //根据均值和标准差建立一个正态分布
        if(stdeviation <= 0){
            stdeviation = 0.0001;//给予一个很小的方差，以能够成功创建一个正态分布
        }
        distribution = new NormalDistribution(mean, stdeviation);
/*        try {
            distribution = new NormalDistribution(mean, stdeviation);
        }
        catch (Exception e){
            System.out.println();
        }*/
    }
    public boolean isNormalDistri(){
        return !TestUtils.kolmogorovSmirnovTest(distribution, arr,CONFIDENCE);
    }
    public boolean isDawnToThisDistri(double i){
        if (stdeviation == 0) return i == mean;
        double limit = (i - mean) / stdeviation;
        return Math.abs(limit) < K;
    }
    public double getMean() {
        return mean;
    }

    public double getStdeviation() {
        return stdeviation;
    }

    public NormalDistribution getDistribution() {
        return distribution;
    }

    public static void main(String[] args) {
        double[] arr = {1, 2, 0, 1};
        NormalDistributionTest test = new NormalDistributionTest(arr);
        System.out.println(test.getMean() + " " + test.getStdeviation() + " " + test.getDistribution().getNumericalVariance());
    }
}
