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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import elong.CrazyLink.CrazyLinkConstent;
import elong.CrazyLink.R;
import elong.CrazyLink.Draw.DrawAnimal;
import elong.CrazyLink.Draw.DrawSingleScore;
import elong.CrazyLink.Draw.DrawTip1;
import elong.CrazyLink.Draw.DrawDisappear;
import elong.CrazyLink.Draw.DrawExchange;
import elong.CrazyLink.Draw.DrawFill;
import elong.CrazyLink.Draw.DrawGrid;
import elong.CrazyLink.Draw.DrawLoading;
import elong.CrazyLink.Draw.DrawScore;
import elong.CrazyLink.Interface.IControl;


public class ControlCenter {

	Context mContext;
	
	static int mPic[][];				//��Ӧ������ʾ��ͼƬ������DrawAnimal��Ⱦ
	static int mStatus[][];		//0������ʾ��1����ʾ���2:��ʾ������Ч��3:������Ч��4:������Ч
	
	static int mSingleScoreW = 0;	//��ʾ���ν�����λ��
	static int mSingleScoreH = 0;
	
    int animalTextureId;				//�����ز�����id
    static int[] loadingTextureId = new int[10];			//���ض����ز�����id
    int gridTextureId;				//�����ز�����id
    int scoreTextureId;
    int congratulationTextureId;
    
	static DrawAnimal drawAnimal;
	static public DrawLoading drawLoading;
	static public DrawGrid drawGrid;
	static public DrawExchange drawExchange;
	static public DrawDisappear drawDisappear;
	static public DrawFill drawFill;
	static public DrawScore drawScore;
	static public DrawSingleScore drawSingleScore;
	static public DrawTip1 drawTip1;

	
	static Score score;
	
	
	public static boolean mIsLoading = false;
	
	ArrayList<IControl> mControlList = new ArrayList<IControl>(); 

	public ControlCenter(Context context)
	{
		mContext = context;
		score = new Score();
	    mPic = new int[(int) CrazyLinkConstent.GRID_NUM][(int) CrazyLinkConstent.GRID_NUM];
	    mStatus = new int[(int) CrazyLinkConstent.GRID_NUM][(int) CrazyLinkConstent.GRID_NUM];
		init();
	}
	
	//��ʼ���߼�����֤��ʼ���Ժ��״̬û�д�������״̬��
	void init()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				mPic[i][j] = getRandom();
				while (isInLine(i,j))
				{
					mPic[i][j] = getRandom();	
				}
				mStatus[i][j] = 1;
			}
		}
		
	}

	//����1~7�������
	static int getRandom()
	{
		int data = (int) (Math.random()*100);
		return (data % 7) + 1;
	}
	
	static boolean isInLineX(int col, int row)
	{
		int picId = mPic[col][row];
		if(0 == col)
		{
			if(picId == mPic[col+1][row] && picId == mPic[col+2][row])
			{
				return true;
			}
		}
		else if(1 == col)
		{
			if((picId == mPic[col-1][row] && picId == mPic[col+1][row])
					|| (picId == mPic[col+1][row] && picId == mPic[col+2][row]))
			{
				return true;
			}
		}
		else if(col > 1 && col < 5)
		{
			if((picId == mPic[col-2][row] && picId == mPic[col-1][row])
					|| (picId == mPic[col-1][row] && picId == mPic[col+1][row])
					|| (picId == mPic[col+1][row] && picId == mPic[col+2][row]))
			{
				return true;
			}
		}
		else if(5 == col)
		{
			if((picId == mPic[col-2][row] && picId == mPic[col-1][row])
					|| (picId == mPic[col-1][row] && picId == mPic[col+1][row]))
			{
				return true;
			}
		}
		else if(6 == col)
		{
			if(picId == mPic[col-1][row] && picId == mPic[col-2][row])
			{
				return true;
			}
		}
		return false;
	}
	
	static boolean isInLineY(int col, int row)
	{
		int picId = mPic[col][row];
		if(0 == row)
		{
			if(picId == mPic[col][row+1] && picId == mPic[col][row+2])
			{
				return true;
			}
		}
		else if(1 == row)
		{
			if((picId == mPic[col][row-1] && picId == mPic[col][row+1])
					|| (picId == mPic[col][row+1] && picId == mPic[col][row+2]))
			{
				return true;
			}
		}
		else if(row > 1 && row < 5)
		{
			if((picId == mPic[col][row-2] && picId == mPic[col][row-1])
					|| (picId == mPic[col][row-1] && picId == mPic[col][row+1])
					|| (picId == mPic[col][row+1] && picId == mPic[col][row+2]))
			{
				return true;
			}
		}
		else if(5 == row)
		{
			if((picId == mPic[col][row-2] && picId == mPic[col][row-1])
					|| (picId == mPic[col][row-1] && picId == mPic[col][row+1]))
			{
				return true;
			}
		}
		else if(6 == row)
		{
			if(picId == mPic[col][row-1] && picId == mPic[col][row-2])
			{
				return true;
			}
		}
		return false;
	}

	//��������㷨
	static boolean isInLine(int col, int row)
	{
		return isInLineX(col, row) || isInLineY(col, row);
	}

	//�����еĶ����ǳ���
	static void markInLine()
	{
		int markCount = 0;
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (isInLine(i, j))	
				{
					mStatus[i][j] = 4;
					markCount++;
				}
			}
		}		
		if (markCount > 0)
		{
			drawDisappear.control.start();
			score.increase();
			score.increase(markCount);			
		}
		else
		{
			score.reset();
		}
	}
	
	//���������־
	static void clearInline()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				mStatus[i][j] = 1;
			}
		}				
	}
	
	//������ɺ�Ҫ����Ӧ�ĸ�����Ϊ0
	static int clearPic()
	{
		int clearCount = 0;
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (4 == mStatus[i][j]) 
				{
					mPic[i][j] = 0;
					clearCount++;
				}
			}
		}				
		return clearCount;
	}
	
	//�ж��Ƿ���Ҫ����������Ч
	static boolean isNeedClear()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (isInLine(i,j))
				{
					return true;
				}
			}
		}						
		return false;
	}
	
	//��ǵ�����Ч
	static void fillGrid(int col, int row)
	{
		if(0 == mPic[col][row])
		{
			for(int i = row; i < (int)CrazyLinkConstent.GRID_NUM; i++)
			{
				mStatus[col][i] = 3;
			}
		}
	}
	
	//ѭ�����Ҫ��䣨���䣩�ĸ���
	static void markFill()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				mStatus[i][j] = 1;
			}
		}						
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				fillGrid(i,j);
			}
		}				
		fillPic();
		drawFill.control.start();
	}
	
	//�Ƿ���Ҫ��䣨���䣩
	static boolean isNeedFill()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if(0 == mPic[i][j])
				{
					return true;
				}
			}
		}						
		return false;
	}
	
	//�����㷨
	static void fillPic()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if(0 == mPic[i][j])		//0����ø����ǿյģ���Ҫ����
				{
					if(j < (int)CrazyLinkConstent.GRID_NUM - 1)
					{
						//����һ���е���
						mPic[i][j] = mPic[i][j + 1];
						mPic[i][j + 1] = 0;
					}
					else
					{
						//�������������ߵ�һ�У�������������䶯��
						mPic[i][j] = getRandom();	
					}
				}
			}
		}				
			
	}

	
	
	public void draw(GL10 gl)
	{
		drawLoading.draw(gl); 
		if(mIsLoading) return;
		
		drawScore.draw(gl,score.getScore());
		drawSingleScore.draw(gl, mSingleScoreW, mSingleScoreH, score.getAward());
		drawTip1.draw(gl);
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++)
			{
				switch (mStatus[i][j])
				{
				case 1:	//������ʾ
					drawAnimal.draw(gl,mPic[i][j],i,j);
					break;
				case 2:	//������Ч
					drawExchange.draw(gl);		
					break;
				case 3:	//������Ч
					drawFill.draw(gl, mPic[i][j], i, j);
					break;
				case 4:	//������Ч
					drawDisappear.draw(gl, mPic[i][j], i, j);
					break;
				}				                  
			}
		}
    	drawGrid.draw(gl);
		
	}
	//��ʼ���������
	public void initTexture(GL10 gl)
	{
    	animalTextureId = initTexture(gl, R.drawable.animal);	//��ʼ���������    	
    	for(int i = 0; i < 10; i++)
    	{
    		loadingTextureId[i] = initTexture(gl, R.drawable.loading_01 + i);
    	}    	
    	gridTextureId = initTexture(gl, R.drawable.grid);
    	scoreTextureId = initTexture(gl, R.drawable.number);
    	congratulationTextureId = initTexture(gl, R.drawable.word);
	}
	
	//��ʼ����Ⱦ����
	public void initDraw(GL10 gl)
	{
    	drawAnimal = new DrawAnimal(animalTextureId);			//���������زĶ���    	
    	drawGrid = new DrawGrid(gridTextureId);					//���������زĶ���
    	drawDisappear = new DrawDisappear(drawAnimal);
    	drawFill = new DrawFill(drawAnimal);
    	drawScore = new DrawScore(scoreTextureId);
    	drawSingleScore = new DrawSingleScore(gl);
    	drawTip1 = new DrawTip1(congratulationTextureId);
    	drawLoading = new DrawLoading(loadingTextureId);		//�������ض����ز�
    	drawExchange = new DrawExchange(drawAnimal);
    	
    	regist(drawDisappear.control);
    	regist(drawExchange.control);
    	regist(drawFill.control);
    	regist(drawLoading.control);
    	regist(drawSingleScore.control);
    	regist(drawTip1.control);
	}

	//��ʼ������ķ���
	private int initTexture(GL10 gl, int drawableId)
	{
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		int currTextureId = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);//ָ����С���˷���
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);//ָ���Ŵ���˷���
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);//ָ��S��������ͼģʽ
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);//ָ��T��������ͼģʽ
		InputStream is = mContext.getResources().openRawResource(drawableId);
		Bitmap bitmapTmp;
		try{
			bitmapTmp = BitmapFactory.decodeStream(is);
		}
		finally{
			try{
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
		bitmapTmp.recycle();
		return currTextureId;
	}

	
	//��Ϣ����
	public static final int EXCHANGE_START = 1;
	public static final int EXCHANGE_END = 2;
	public static final int LOADING_START = 3;
	public static final int LOADING_END = 4;
	public static final int DISAPPEAR_END = 5;
	public static final int FILL_END = 6;


	
	//��Ϣ����
    public static Handler mHandler = new Handler(){   
        @Override  
		public void handleMessage(Message msg) 
		{
			    // process incoming messages here
			switch(msg.what)
			{
			case EXCHANGE_START:
			{
				Bundle b = msg.getData();
				int col1 = b.getInt("col1");
				int col2 = b.getInt("col2");
				int row1 = b.getInt("row1");
				int row2 = b.getInt("row2");				
		    	mStatus[col1][row1] = 2;			//���ڽ���״̬
		    	mStatus[col2][row2] = 0;
		    	setSingleScorePosition(col1, row1);
		    	drawExchange.init(mPic[col1][row1], col1, row1, mPic[col2][row2], col2, row2);
				break;
			}
			case EXCHANGE_END:
			{
				Bundle b = msg.getData();
				int col1 = b.getInt("col1");
				int col2 = b.getInt("col2");
				int row1 = b.getInt("row1");
				int row2 = b.getInt("row2");
				int picId = mPic[col1][row1];
				mPic[col1][row1] = mPic[col2][row2];
				mPic[col2][row2] = picId;
		    	mStatus[col1][row1] = 1;			//����״̬���
		    	mStatus[col2][row2] = 1;
				
				markInLine();				
				break;
			}
			case LOADING_START:		    	
		    	drawLoading.control.start();
		    	break;
			case LOADING_END:
				mIsLoading = false;
				break;			
			case DISAPPEAR_END:
			{
				int clearCnt = clearPic();
				score.award(clearCnt);
				if(score.getAward() > 0)
				{
					drawTip1.control.start(clearCnt);
					drawSingleScore.control.start();
				}
				clearInline();
				markFill();
				break;
			}
			case FILL_END:				
				if(isNeedFill())
				{
					markFill();
				}
				else
				{
					if(isNeedClear())
					{
						markInLine();
					}
				}
				break;
			}
		}
    };
    
    static void setSingleScorePosition(int col, int row)
    {
    	if(drawSingleScore.control.isRun()) return;
    	mSingleScoreW = col;
    	mSingleScoreH = row;
    }
    
    void regist(IControl control)
    {
    	if(control != null)	mControlList.add(control);
    }
    
    public void run()
    {
		IControl control = null;		
		for(int i=0;i<mControlList.size();i++){ 
			control = mControlList.get(i);
			control.run();
		}
    }
	
}
