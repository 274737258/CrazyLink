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
public class CtlAutoTip extends CtlBase{
	
	int mPicId = 1;
	int mTimeCnt = 0;

	public void run()
	{
		if(mStop) return;
		mTimeCnt++;
		if (1 == (mTimeCnt % 2)) return;		//��Ƶ
		mPicId++;
		if (mPicId > 4) mPicId = 1;
	}
	
	public int getPicId()
	{
		return mPicId;
	}		
	
	public void start()
	{
		mStop = false;
	}
	
	public void end()
	{
		mStop = true;
	}
	
	public boolean isRun()
	{
		return !mStop;
	}
}

