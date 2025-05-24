package com.rm.service.message;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService{
	
	
	
	
    private String makeRandomNumber() {
        int authNo = (int)(Math.random() * (9999 - 1000 + 1)) + 1000;

        return Integer.toString(authNo);
    }

}
