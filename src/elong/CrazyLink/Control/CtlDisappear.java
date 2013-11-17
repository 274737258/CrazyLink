/**********************************************************
 * ��Ŀ���ƣ�ɽկ������������Ϸ7�ս̳�
 * ��          �ߣ�֣����
 * ��Ѷ΢����SuperCube3D
 * ��          �ڣ�2013��10��
 * ��          ������Ȩ����   ��Ȩ�ؾ�
 * ��Դ���빩�����о�ѧϰOpenGL ES����AndroidӦ���ã�
 * ����ȫ���򲿷�������ҵ��;
 ********************************************************/

package elong.CrazyLink.Control;
import android.os.Message;
import elong.CrazyLink.Core.ControlCenter;

public class CtlDisappear extends CtlBase{

	int mCount = 6;
	int mTimeCnt = 0;

	public void run()
	{
		if (!mStop)
		{
			mTimeCnt++;
			if (1 == (mTimeCnt % 5)) return;		//��Ƶ
			mCount--;
			if(0 == mCount)
			{
				mStop = true;
				Message msg = new Message();
			    msg.what = ControlCenter.DISAPPEAR_END;
			    ControlCenter.mHandler.sendMessage(msg);
			}
		}
	}
	
	public void start()
	{
		mCount = 10;
		super.start();
	}
	
	public int getCount()
	{
		return mCount;
	}

}

