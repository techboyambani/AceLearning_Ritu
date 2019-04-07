package com.example.dell.acelearning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsAccessorAdapter extends FragmentPagerAdapter {
    public TabsAccessorAdapter(FragmentManager fm) {
        super(fm);
    }
    String subject;
    public void setSubject(String subject)
    {
        this.subject=subject;
    }
    public String getSubject()
    {
        return subject;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        switch (position)
        {
            case 0:
                bundle.putString("subject", subject);
                student_lectures mstudent_lectures= new student_lectures();
                mstudent_lectures.setArguments(bundle);
                return mstudent_lectures;


            case 1:
                bundle.putString("subject", subject);
                student_notes mstudent_notes= new student_notes();
                mstudent_notes.setArguments(bundle);
                return mstudent_notes;


            case 2:
                bundle.putString("subject", subject);
                student_assignments mstudent_assignments= new student_assignments();
                mstudent_assignments.setArguments(bundle);
                return mstudent_assignments;
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                    return "Lectures";

            case 1:return "Notes";

            case 2:
                return "Assignments";

            default: return null;
        }
    }
}
