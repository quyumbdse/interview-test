
package com.example.interview;

import com.example.interview.Interview.CreateRadioProfileRequest;
import com.example.interview.Interview.DeleteRadioProfileRequest;
import com.example.interview.Interview.SetRadioLocationRequest;

interface RadioProfileRepository {

    CreateRadioProfileRequest findById(int id);

    SetRadioLocationRequest setLocationById(int id);

    DeleteRadioProfileRequest deleteProfile(int id);
  }