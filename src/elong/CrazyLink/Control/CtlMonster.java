/**********************************************************
 * ��Ŀ���ƣ�ɽկ������������Ϸ7�ս̳�
 * ��          �ߣ�֣����
 * ��Ѷ΢����SuperCube3D
 * ��          �ڣ�2013��11��
 * ��          ������Ȩ����   ��Ȩ�ؾ�
 * ��Դ���빩�����о�ѧϰOpenGL ES����AndroidӦ���ã�
 * ����ȫ���򲿷�������ҵ��;
 ********************************************************/

package elong.CrazyLink.Control;


//�Զ���ʾЧ��
public class CtlMonster extends CtlBase{
	
	int mPicId = 1;
	int mTimeCnt = 0;

	public void run()
	{
		mTimeCnt++;
		if (1 != (mTimeCnt % 3)) return;		//��Ƶ
		mPicId++;
		if (mPicId > 7) mPicId = 1;
	}
	
	public int getPicId()
	{
		return mPicId;
	}			
}


