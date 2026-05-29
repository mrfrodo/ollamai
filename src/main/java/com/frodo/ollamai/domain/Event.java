package com.frodo.ollamai.domain;

import java.time.Instant;

public record Event(
        String eventId,
        String sourceNode,
        String eventType, // e.g., "BGP_PEER_DOWN", "POWER_FAIL", "HEARTBEAT_TIMEOUT"
        String severity,  // INFO, WARNING, CRITICAL
        Instant timestamp,
        String description
) {}