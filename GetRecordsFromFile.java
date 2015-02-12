package algorithms;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import resource.dao.EntryDao;
import resource.depart57SessionFactory.LoadFromMysql;
import resource.entity.StockBean;

import java.io.*;
import java.util.List;

/**
 * Created by qt on 2015/2/12.
 */
public class GetRecordsFromFile {
    public static double[] getFromFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        String line = reader.readLine();
        String[] arr = line.split("\t");
        int len = arr.length;
        double[] res = new double[len];
        for (int i = 0; i < len; i++) {
            res[i] = Double.parseDouble(arr[i]);
        }
        return res;
    }
}
