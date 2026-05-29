package com.frodo.ollamai.domain;

public record Alarm(
        String alarmId,
        String affectedNode,
        String probableCause,
        String lifecycleStatus // OPEN, SUPPRESSED, CLEARED
) {}