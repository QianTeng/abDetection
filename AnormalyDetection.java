package algorithms;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.util.Arrays;

/**
 * Created by qt on 2015/2/9.
 */
public class AnormalyDetection {
    private final static int INITWINSIZE = 2;
    private final static int MAXWINSIZE = 10;
    private final static int EXPANDSIZE = 1;
    public double[] detect(double[] data){
        if(data == null){
            return null;
        }
        int len = data.length;
        if(len < 1){
            return null;
        }
        double[] res = new double[len];
        int index = INITWINSIZE;
        while (index < len){
            double[] curBaseArr = Arrays.copyOfRange(data, index - INITWINSIZE, index);
            NormalDistributionTest tmpTest;
            int curOuterNum = 0;
            int curLen = INITWINSIZE;
            while (!(tmpTest = new NormalDistributionTest(curBaseArr)).isNormalDistri() && index + EXPANDSIZE < len) {
                curBaseArr = Arrays.copyOfRange(data, index - INITWINSIZE + EXPANDSIZE, index + EXPANDSIZE);
                index += EXPANDSIZE;
                curLen += EXPANDSIZE;
            }
            while (index < len) {
                if (!tmpTest.isDawnToThisDistri(data[index])){
                    curOuterNum ++;
                    res[index] = data[index];
                    if (curOuterNum * 2 > curLen) break;
                }
                else {
                    if(curLen < MAXWINSIZE){
                        curBaseArr = Arrays.copyOfRange(data, index - curLen, index + 1);
                        tmpTest = new NormalDistributionTest(curBaseArr);
                        curLen ++;
                    }
                }
                index ++;
            }
            if(index < len){
                index += INITWINSIZE;
                continue;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String path = "e:\\paperData\\restt.txt";
        String xlspath = "e:\\paperData\\restt.xlsx";
        AnormalyDetection detect = new AnormalyDetection();
        double[] old = null;
        /*try {
            old = GetStocks.getFromFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        old = GetRecordsFromSql.getFromSql();
        double[] res = detect.detect(old);
        System.out.println(res.length);

        try {
            ShowInExcle.changeXls(xlspath, old, res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
