
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

import android.os.Message;
import elong.CrazyLink.Core.ControlCenter;

public class CtlLifeDel extends CtlBase{
	
	int mDeltaW = 0;
	int mDeltaH = 0;
	float mDeltaX = 0;
	float mDeltaY = 0;
	int mStep = 0;
	int mKeep = 0;
	int mPicId = 0;
	int mGoodCnt = 0;
	int mTimeCnt = 0;
	
	public void run()
	{
		int maxW = 48;
		int minW = 16;
		int deltaW = 4;
		int deltaH = 4;
		float deltaY = 0.2f;
		if(!mStop)
		{
			mTimeCnt++;
			if (1 == (mTimeCnt %2))
			{
				mPicId++;
				if (mPicId > 15) mPicId = 0;
			}

			if(0 == mStep)
			{
				mDeltaW += deltaW;
				mDeltaH += deltaH;
				mDeltaY = mDeltaY - deltaY;
				if (mDeltaW >= maxW)
				{
					mStep = 1;
				}
			}
			else if(1 == mStep)
			{
				mKeep++;
				if(mKeep >= 30)
				{
					mKeep = 0;
					mStep = 2;
				}				
			}
			else if(2 == mStep)
			{
				mDeltaW -= deltaW;
				mDeltaH -= deltaH;
				
				mDeltaY = mDeltaY + deltaY;
				if (mDeltaW <= minW)
				{
					mStep = 3;
				}				
			}
			else if(3 == mStep)
			{
				mStop = true;
				sendMsg();
			}
		}		
	}
	
	public int getW()
	{
		return mDeltaW;
	}
	
	public int getH()
	{
		return mDeltaH;
	}
	
	public float getX()
	{
		return mDeltaX;
	}
	
	public float getY()
	{
		return mDeltaY;
	}
	
	public void init(int life)
	{
		if(!mStop) return;
		mDeltaW = 0;
		mDeltaH = 0;
		//���ݵ�ǰ����ֵ���趨Ҫ����������ͼ�����ڵ�λ��
		if(life > 3) mDeltaX = 5;
		else if(3 == life) mDeltaX = 2;
		else if(2 == life) mDeltaX = 3;
		else if(1 == life) mDeltaX = 4;
		mDeltaY = 10;
		mStep = 0;	
		mPicId = 0;
		super.start();
	}
		
	public int getPicId()
	{
		return mPicId;
	}
	
	public void sendMsg()
	{
		Message msg = new Message();
		msg.what = ControlCenter.LIFEDEL_END;
	    ControlCenter.mHandler.sendMessage(msg);			
	}
}
