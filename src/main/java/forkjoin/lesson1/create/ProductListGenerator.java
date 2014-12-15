package forkjoin.lesson1.create;

import java.util.ArrayList;
import java.util.List;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月15日 下午5:02:50
 *@version 1.0
 *@Description:产品列表生成器
 */
public class ProductListGenerator {
	public static  List<Product> generate (int size) {
		List<Product> ret=new ArrayList<Product>();
		for (int i = 0; i <size; i++) {
			Product  p= new Product();
			p.setName("product "+i);
			p.setPrice(10);
			ret.add(p);
		}
		return ret;
	}
}
