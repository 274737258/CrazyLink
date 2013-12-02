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

public class CtlFill extends CtlBase{
	int mDeltaY;	
	int mStep = 25;				//ƫ�Ʋ���	

	public CtlFill()
	{
		mStop = false;
		mDeltaY = 100;		
	}
	
	public void run()
	{
		//��ƫ�Ʒ�Χ�޶���-0.5~0.5�䣬����ָ�����伴��ͷ
		if(!mStop)
		{
			mDeltaY -= mStep;		
			if(mDeltaY <= 0)
			{
				mStop = true;
			}
			if(mStop)
			{
				sendMsg();
			}			
		}
	}
		
	public float getY()
	{
		return mDeltaY/100.0f;
	}

	public void start()
	{
		mDeltaY = 100;
		super.start();
	}

	public void sendMsg()
	{
		Message msg = new Message();
	    msg.what = ControlCenter.FILL_END;
	    ControlCenter.mHandler.sendMessage(msg);
	}
}

