
/**********************************************************
 * 项目名称：山寨“爱消除”游戏7日教程
 * 作          者：郑敏新
 * 腾讯微博：SuperCube3D
 * 日          期：2013年11月
 * 声          明：版权所有   侵权必究
 * 本源代码供网友研究学习OpenGL ES开发Android应用用，
 * 请勿全部或部分用于商业用途
 ********************************************************/
package elong.CrazyLink.Control;

import android.os.Message;
import elong.CrazyLink.Core.ControlCenter;

public class CtlLifeAdd extends CtlBase{
	
	int mDeltaW = 0;
	int mDeltaH = 0;
	float mDeltaX = 0;
	float mDeltaY = 0;
	float mCol = 0;
	int mStep = 0;
	int mKeep = 0;
	int mPicId = 0;
	int mGoodCnt = 0;
	int mTimeCnt = 0;
	
	public void run()
	{
		int maxW = 48;
		int minW = 12;
		int deltaW = 4;
		int deltaH = 4;
		if(!mStop)
		{
			mTimeCnt++;
			if (1 == (mTimeCnt %2))
			{
				mPicId++;
				if (mPicId > 3) mPicId = 0;
			}

			if(0 == mStep)
			{
				mDeltaW += deltaW;
				mDeltaH += deltaH;
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
				
				mDeltaY ++;
				mDeltaX = mDeltaY / 2;
				if(mDeltaX >= mCol) mDeltaX = mCol;	//瞄准新添加生命出现的位置
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
		mDeltaX = 0;
		mDeltaY = 0;
		//根据当前生命值设定新加的图案出现的位置
		if(life >= 3) mCol = 5;
		else if(2 == life) mCol = 2;
		else if(1 == life) mCol = 3;
		else if(0 == life) mCol = 4;
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
		msg.what = ControlCenter.LIFEADD_END;
	    ControlCenter.mHandler.sendMessage(msg);			
	}
}
