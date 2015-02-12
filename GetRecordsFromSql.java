package algorithms;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import resource.dao.EntryDao;
import resource.depart57SessionFactory.LoadFromMysql;
import resource.entity.StockBean;

import java.util.List;

/**
 * Created by qt on 2015/2/12.
 */
public class GetRecordsFromSql {
    public static double[] getFromSql(){
        SqlSessionFactory factory = LoadFromMysql.getSessionFactory();
        SqlSession session = factory.openSession();
        EntryDao dao = session.getMapper(EntryDao.class);
        List<StockBean> stockBeans = dao.findAllRecords();
        int i = 0;
        double[] res = new double[stockBeans.size()];
        for (StockBean stockBean : stockBeans) {
            res[i++] = stockBean.getVolume();
        }
        return res;
    }
}
