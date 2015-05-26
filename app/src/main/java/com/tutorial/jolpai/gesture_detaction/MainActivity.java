package com.tutorial.jolpai.gesture_detaction;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;

import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
,GestureOverlayView.OnGesturePerformedListener{

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    private VelocityTracker mVelocityTracker = null;
    private GestureLibrary gestureLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
       //// mDetector = new GestureDetectorCompat(this, this);
        // Set the gesture detector as the double tap
        // listener.
     //  // mDetector.setOnDoubleTapListener(MainActivity.this);

        GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
        View inflate = getLayoutInflater().inflate(R.layout.activity_main, null);
        gestureOverlayView.addView(inflate);
        gestureOverlayView.addOnGesturePerformedListener(this);
       // gestureLib = GestureLibraries.fromRawResource(this,R.raw.gestures);

       /* if (!gestureLib.load()) {
            finish();
        }*/


        setContentView(gestureOverlayView);
    }

  @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.d(DEBUG_TAG, "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(DEBUG_TAG, "Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(DEBUG_TAG, "Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(DEBUG_TAG, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(DEBUG_TAG, "Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }*/


     /* this.mDetector.onTouchEvent(event);
      // Be sure to call the superclass implementation
      return super.onTouchEvent(event);*/

      int index = event.getActionIndex();
      int action = event.getActionMasked();
      int pointerId = event.getPointerId(index);

      switch(action) {
          case MotionEvent.ACTION_DOWN:
              if(mVelocityTracker == null) {
                  // Retrieve a new VelocityTracker object to watch the velocity of a motion.
                  mVelocityTracker = VelocityTracker.obtain();
              }
              else {
                  // Reset the velocity tracker back to its initial state.
                  mVelocityTracker.clear();
              }
              // Add a user's movement to the tracker.
              mVelocityTracker.addMovement(event);
              break;
          case MotionEvent.ACTION_MOVE:
              mVelocityTracker.addMovement(event);
              // When you want to determine the velocity, call
              // computeCurrentVelocity(). Then call getXVelocity()
              // and getYVelocity() to retrieve the velocity for each pointer ID.
              mVelocityTracker.computeCurrentVelocity(1000);
              // Log velocity of pixels per second
              // Best practice to use VelocityTrackerCompat where possible.
              Log.d("", "X velocity: " +
                      VelocityTrackerCompat.getXVelocity(mVelocityTracker,
                              pointerId));
              Log.d("", "Y velocity: " +
                      VelocityTrackerCompat.getYVelocity(mVelocityTracker,
                              pointerId));
              break;
          case MotionEvent.ACTION_UP:
          case MotionEvent.ACTION_CANCEL:
              // Return a VelocityTracker object back to be re-used by others.
            //  mVelocityTracker.recycle();
              break;
      }
      return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.e(DEBUG_TAG, "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        Log.e(DEBUG_TAG, "Single Tapped");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.e(DEBUG_TAG, "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e(DEBUG_TAG, "onFling");
        Log.e(DEBUG_TAG,"Event e1:"+e1);
        Log.e(DEBUG_TAG,"Event e2:"+e2);
        Log.e(DEBUG_TAG,"velocityX:"+velocityX);
        Log.e(DEBUG_TAG,"velocityY:"+velocityY);

        if(velocityX<velocityY) {

            Log.e(DEBUG_TAG,"To Down");
        }
        else{
            Log.e(DEBUG_TAG,"To Up");
        }
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.e(DEBUG_TAG, "onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.e(DEBUG_TAG, "onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.e(DEBUG_TAG, "onDoubleTapEvent");
        return true;
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        /*ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        for (Prediction prediction : predictions) {
            if (prediction.score > 1.0) {
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
                        .show();
            }
        }*/
    }
}
