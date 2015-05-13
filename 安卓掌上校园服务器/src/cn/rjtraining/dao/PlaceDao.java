package cn.rjtraining.dao;


import cn.rjtraining.model.Page;
import cn.rjtraining.model.Place;
public interface PlaceDao {
/*
 * ��user������Щʲô������
 * 1����½��ʱ��Ҫ����user��
 * 2���޸��û������ʱ��Ҫ����user ��
 * 3��ÿ�����ѧ��������ʦ��ʱ�򣬶�Ҫͬʱ��user����������ݡ�
 * 4��ɾ��user �����Ϣ
*/
/**
 * ��ѯuser�����Ƿ����ĳ�û�
 * @param id �û���id�������ѧ����id�ͱ�ʾѧ�ţ��������ʦ��id �ͱ�ʾ���š�
 * @return ������ڴ��û������ش��û������򷵻ؿ�ֵ
 */
	Place find(int id);
/**
 * �޸��û�����Ϣ��������½���롢�û�������ɫ��
 * @param user һ��ʵ��
 * @return ����޸ĳɹ�����true����֮������false��
 */
	boolean update(Place place);
/**
 * ���û������������
 * @param user һ��������ʵ�塣
 * @return ��ӳɹ�����1�����򷵻�0
*/
	Page search(int pageNow);
	/**
	 * ִ����Ϣ�Ĳ�ѯ����
	 * @param type ���߷���������ʲô��Ϊ��ѯ������
	 * @param key  ���߷���������ѯ�Ĺؼ���
	 * @return  ����һ��page����ʾ�����ǵĴ�����
	 */
	Page search(String type,String key,int pageNow);
	
	int insert(Place place);
/**
 * ����Idɾ���û����е���Ϣ
 * @param id �û����˺���Ϣ
 * @return  ɾ���ɹ�����true������false
*/
	boolean deleteById(int id);
	
	int placesum();
	//�ص�����
}
//�ýӿڹ涨�˺��û��йصķ����������ǳ�����������Ҫ��ɲ��֣��ӿڵ���д���ܹ�����һ������Ա��������������Ե�������
