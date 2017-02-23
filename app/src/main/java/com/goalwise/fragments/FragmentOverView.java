package com.goalwise.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.goalwise.R;
import com.goalwise.adapter.AdapterFirstRow;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Techno Blogger on 19/2/17.
 */

public class FragmentOverView extends Fragment implements View.OnClickListener {


    private RelativeLayout mRelativeDraft, mRelativeInvest, mRelativeLowRisk, mRelativeModerateRisk, mRelativeHighRisk,
            mRelativeRisk, mRelativeMonthly, mRelativeLumpSum, mRelativeTimeHorizon;
    private TextView mTextEquity, mTextDebt, mTextRisk, mTextSaveAsDraft, mTextSaveAndInvest;
    private EditText mTextLacs, mTextThousand;
    private SeekBar mSeekBarLacs;
    private ViewPager mViewPagerFirstRow;
    private String stringRiskText = "Want to know how much you should save ? Use this ";
    private String mStringRetirementTool = "<u><font color='#736FFF'>RETIREMENT TOOL</font></u>";
    private CirclePageIndicator circlePageIndicator;
    private int currentIndex = 0;
    private TextView textPopUp;
    private View popupLayout;
    private PopupWindow popupWindow;
    String stringThousand, stringLacs;
    public float mProgressText;
    public int mProgressValue;
    net.qiujuer.genius.ui.widget.SeekBar mSeekBarThousand, mSeekbarRisk;

    private boolean lackChanging;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, null);

        // For First Row
        mViewPagerFirstRow = (ViewPager) rootView.findViewById(R.id.vieaPagerRowFirst);


        // For Second Row
        mRelativeLowRisk = (RelativeLayout) rootView.findViewById(R.id.relativeLowRisk);
        mRelativeModerateRisk = (RelativeLayout) rootView.findViewById(R.id.relativeModerateRisk);
        mRelativeHighRisk = (RelativeLayout) rootView.findViewById(R.id.relativeHighRisk);
        mRelativeRisk = (RelativeLayout) rootView.findViewById(R.id.relativeRisk);
        mTextDebt = (TextView) rootView.findViewById(R.id.textDebt);
        mTextEquity = (TextView) rootView.findViewById(R.id.textEquity);
        mSeekbarRisk = (net.qiujuer.genius.ui.widget.SeekBar) rootView.findViewById(R.id.seekBarRisk);

        mSeekbarRisk.setOnSeekBarChangeListener(new net.qiujuer.genius.ui.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(net.qiujuer.genius.ui.widget.SeekBar seekBar, int progress, boolean fromUser) {

                if (progress > 50 && progress < 70) {
                    mRelativeLowRisk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mRelativeModerateRisk.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mRelativeHighRisk.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else if (progress > 70 && progress < 85) {
                    mRelativeModerateRisk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mRelativeLowRisk.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mRelativeHighRisk.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else if (progress > 85 && progress < 100) {
                    mRelativeHighRisk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mRelativeModerateRisk.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mRelativeLowRisk.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }

            }

            @Override
            public void onStartTrackingTouch(net.qiujuer.genius.ui.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(net.qiujuer.genius.ui.widget.SeekBar seekBar) {

            }

        });

        // For Third Row
        mTextThousand = (EditText) rootView.findViewById(R.id.textThousand);
        mSeekBarThousand = (net.qiujuer.genius.ui.widget.SeekBar) rootView.findViewById(R.id.seekBarThousand);
        mRelativeMonthly = (RelativeLayout) rootView.findViewById(R.id.relativeMonthly);


        // For Forth Row
        mTextLacs = (EditText) rootView.findViewById(R.id.textLacs);
        mSeekBarLacs = (SeekBar) rootView.findViewById(R.id.seekBarLacs);
        mRelativeLumpSum = (RelativeLayout) rootView.findViewById(R.id.relativeLumpSum);


        // For Sixth Row
        mRelativeDraft = (RelativeLayout) rootView.findViewById(R.id.relativeSaveDraft);
        mRelativeInvest = (RelativeLayout) rootView.findViewById(R.id.relativeSaveInvest);

        mTextRisk = (TextView) rootView.findViewById(R.id.textRiskDescription);
        mTextSaveAsDraft = (TextView) rootView.findViewById(R.id.textSaveAsDraft);
        mTextSaveAndInvest = (TextView) rootView.findViewById(R.id.textSaveAndInvest);
        mRelativeTimeHorizon = (RelativeLayout) rootView.findViewById(R.id.relativeTimeHorizon);

        mTextRisk.setText((Html.fromHtml(stringRiskText + mStringRetirementTool)));

        // Setting OnClickListener

        // For Second Row
        mRelativeLowRisk.setOnClickListener(this);
        mRelativeModerateRisk.setOnClickListener(this);
        mRelativeHighRisk.setOnClickListener(this);
        mRelativeRisk.setOnClickListener(this);

        // For Third Row
        mRelativeMonthly.setOnClickListener(this);

        // For Forth Row
        mRelativeLumpSum.setOnClickListener(this);

        // For Sixth Row
        mRelativeDraft.setOnClickListener(this);
        mRelativeInvest.setOnClickListener(this);
        mRelativeTimeHorizon.setOnClickListener(this);

        // For First Row
        final AdapterFirstRow adapter = new AdapterFirstRow
                (getActivity().getSupportFragmentManager(), 2);
        mViewPagerFirstRow.setAdapter(adapter);

        // For Circle Pager Indicator
        circlePageIndicator = (CirclePageIndicator) rootView
                .findViewById(R.id.indicator_circle);
        circlePageIndicator.setSnap(true);
        circlePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                currentIndex = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                currentIndex = arg0;
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        circlePageIndicator.setViewPager(mViewPagerFirstRow);
        circlePageIndicator.setFillColor(getResources()
                .getColor(R.color.colorPrimary));
        circlePageIndicator.setStrokeWidth(2);
        circlePageIndicator.setRadius(10);

        // For SeekBar Thousand
        mSeekBarThousand.setMax(9);

        mSeekBarThousand.setOnSeekBarChangeListener(new net.qiujuer.genius.ui.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(net.qiujuer.genius.ui.widget.SeekBar seekBar, final int progress, boolean fromUser) {
                lackChanging = true;
                mTextThousand.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextThousand.setText("" + progress);
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(net.qiujuer.genius.ui.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(net.qiujuer.genius.ui.widget.SeekBar seekBar) {
                lackChanging = false;
            }

        });

        mTextThousand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!lackChanging) {

                    lackChanging = false;

                    String entered = s.toString();
                    if (entered.length() == 0) {
                        return;
                    }
                    int enteredInt = Integer.parseInt(entered);
                    if (enteredInt < 1) {
                        Toast.makeText(getActivity(), "Cannot be less than 1 Thousand", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (enteredInt > 10) {
                        Toast.makeText(getActivity(), "Cannot be more than 10 Thousand", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (enteredInt >= 1 && enteredInt <= 10) {
                        mSeekBarThousand.setProgress(enteredInt);
                    }

                }
            }
        });


        // For SeekBar Lacs

        mSeekBarLacs.setMax(100);
        mSeekBarLacs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                //   mTextLacs.setText(""+(progress + 1));
                lackChanging = true;
                mSeekBarLacs.post(new Runnable() {
                    @Override
                    public void run() {

                        mTextLacs.setText("" + progress);
                    }
                });

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                lackChanging = false;
            }
        });


        mTextLacs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!lackChanging) {

                    lackChanging = false;

                    String entered = s.toString();
                    if (entered.length() == 0) {
                        return;
                    }
                    int enteredInt = Integer.parseInt(entered);
                    if (enteredInt < 10) {
                        Toast.makeText(getActivity(), "Cannot be less than 10 Lacs", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (enteredInt > 100) {
                        Toast.makeText(getActivity(), "Cannot be more than 100 Lacs", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (enteredInt >= 10 && enteredInt <= 100) {
                        mSeekBarLacs.setProgress(enteredInt);
                    }

                }
            }
        });


        return rootView;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativeLowRisk:
                break;

            case R.id.relativeModerateRisk:
                break;
            case R.id.relativeHighRisk:
                break;
            case R.id.relativeSaveDraft:
                mRelativeDraft.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mTextSaveAsDraft.setTextColor(getResources().getColor(R.color.colorWhite));

                mRelativeInvest.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                mTextSaveAndInvest.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.relativeSaveInvest:

                mRelativeDraft.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                mTextSaveAsDraft.setTextColor(getResources().getColor(R.color.colorPrimary));


                mRelativeInvest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mTextSaveAndInvest.setTextColor(getResources().getColor(R.color.colorWhite));

                break;

            case R.id.relativeRisk:
                popupWindow = new PopupWindow(getActivity());
                popupLayout = getActivity().getLayoutInflater().inflate(R.layout.popup_content, null);
                textPopUp = (TextView) popupLayout.findViewById(R.id.tvCaption);
                textPopUp.setText("Risk Loreum Ipsum Loreum Ipsum");
                popupWindow.setContentView(popupLayout);
                // Set content width and height
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                // Closes the popup window when touch outside of it - when looses focus
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                // Show anchored to button
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(v);
                break;

            case R.id.relativeMonthly:
                popupWindow = new PopupWindow(getActivity());
                popupLayout = getActivity().getLayoutInflater().inflate(R.layout.popup_content, null);
                textPopUp = (TextView) popupLayout.findViewById(R.id.tvCaption);
                textPopUp.setText("Monthly Loreum Ipsum Loreum Ipsum");
                popupWindow.setContentView(popupLayout);
                // Set content width and height
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                // Closes the popup window when touch outside of it - when looses focus
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                // Show anchored to button
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(v);
                break;

            case R.id.relativeLumpSum:
                popupWindow = new PopupWindow(getActivity());
                popupLayout = getActivity().getLayoutInflater().inflate(R.layout.popup_content, null);
                textPopUp = (TextView) popupLayout.findViewById(R.id.tvCaption);
                textPopUp.setText("LumpSum Loreum Ipsum Loreum Ipsum");
                popupWindow.setContentView(popupLayout);
                // Set content width and height
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                // Closes the popup window when touch outside of it - when looses focus
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                // Show anchored to button
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(v);
                break;

            case R.id.relativeTimeHorizon:
                popupWindow = new PopupWindow(getActivity());
                popupLayout = getActivity().getLayoutInflater().inflate(R.layout.popup_content, null);
                textPopUp = (TextView) popupLayout.findViewById(R.id.tvCaption);
                textPopUp.setText("Time Horizon Loreum Ipsum Loreum Ipsum");
                popupWindow.setContentView(popupLayout);
                // Set content width and height
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                // Closes the popup window when touch outside of it - when looses focus
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                // Show anchored to button
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(v);
                break;


            default:
                break;

        }

    }


    private void displayPopupWindow(View anchorView) {
        popupWindow = new PopupWindow(getActivity());
        popupLayout = getActivity().getLayoutInflater().inflate(R.layout.popup_content, null);
        textPopUp = (TextView) popupLayout.findViewById(R.id.tvCaption);
        textPopUp.setText("Funds Coming Soon !");
        popupWindow.setContentView(popupLayout);
        // Set content width and height
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // Show anchored to button
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(anchorView);
    }
}
