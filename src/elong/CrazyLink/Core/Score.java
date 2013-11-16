/**********************************************************
 * ��Ŀ���ƣ�ɽկ������������Ϸ7�ս̳�
 * ��          �ߣ�֣����
 * ��Ѷ΢����SuperCube3D
 * ��          �ڣ�2013��10��
 * ��          ������Ȩ����   ��Ȩ�ؾ�
 * ��Դ���빩�����о�ѧϰOpenGL ES����AndroidӦ���ã�
 * ����ȫ���򲿷�������ҵ��;
 ********************************************************/

package elong.CrazyLink.Core;

public class Score {
	int mTotalScore = 0;	//�ܳɼ�
	int mAwardScore = 0;	//���εĽ���
	float mAwardRatio = 0;	//��������
	int mContinueCnt = 0;	//������������
	
	public Score()
	{
		mTotalScore = 0;
		mAwardScore = 0;
	}
	
	//�������򣬿����Լ�����
	public void award(int clearNum)
	{
		int award = 0;
		switch (clearNum)
		{
		case 3:
			award = 100;
			break;
		case 4:
			award = 500;
			break;
		case 5:
			award = 1000;
			break;
		case 6:
			award = 5000;
			break;
		case 7:
			award = 10000;
			break;
		case 8:
			award = 50000;
			break;
		case 9:
			award = 100000;
			break;
		case 10:
			award = 500000;
			break;
		case 11:
			award = 1000000;
			break;
		default:
			if(clearNum > 11)
			{
				award = 500000 * clearNum;
			}
			break;
		}
		awardScore(mAwardRatio*award);
	}
	
	//���ν����ķ���
	public void awardScore(float score)
	{
		mAwardScore = (int)score;
		mTotalScore += (int)score;
	}
	
	//��ȡ�ۼƷ���
	public int getScore()
	{
		return mTotalScore;
	}
	
	//��ȡ���ε÷�
	public int getAward()
	{
		return mAwardScore;
	}
	
	//��ȡ���������Ĵ���
	public int getContinueCnt()
	{
		return mContinueCnt;
	}
	
	//��λ����ϵ��
	public void reset()
	{
		mAwardRatio = 0;
		mContinueCnt = 0;
	}
	
	//��������ϵ��
	public void increase()
	{
		mAwardRatio++;
		mContinueCnt++;
	}
	
	//����һ���������ĸ������ӷ���ϵ��
	public void increase(int clearNum)
	{
		mAwardRatio += (float)clearNum / 5;
	}

}
