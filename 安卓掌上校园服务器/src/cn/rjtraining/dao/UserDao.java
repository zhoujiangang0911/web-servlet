package cn.rjtraining.dao;
import cn.rjtraining.model.User;
public interface UserDao {
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
	User find(int id);
/**
 * �޸��û�����Ϣ��������½���롢�û�������ɫ��
 * @param user һ��ʵ��
 * @return ����޸ĳɹ�����true����֮������false��
 */
	boolean update(User user);
/**
 * ���û������������
 * @param user һ��������ʵ�塣
 * @return ��ӳɹ�����1�����򷵻�0
*/
	int insert(User user);
/**
 * ����Idɾ���û����е���Ϣ
 * @param id �û����˺���Ϣ
 * @return  ɾ���ɹ�����true������false
*/
	boolean deleteById(int id);
	
	int usersum();
}
//�ýӿڹ涨�˺��û��йصķ����������ǳ�����������Ҫ��ɲ��֣��ӿڵ���д���ܹ�����һ������Ա��������������Ե�������
