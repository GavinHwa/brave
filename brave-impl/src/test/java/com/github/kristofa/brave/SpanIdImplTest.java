package com.github.kristofa.brave;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SpanIdImplTest {

    private final static long TRACEID = 10;
    private final static long SPANID = 11;
    private final static Long PARENT_SPANID = new Long(12);

    private SpanIdImpl spanIdImpl;

    @Before
    public void setup() {
        spanIdImpl = new SpanIdImpl(TRACEID, SPANID, PARENT_SPANID);
    }

    @Test
    public void testSpanIdNullParentId() {
        final SpanIdImpl spanIdImpl2 = new SpanIdImpl(TRACEID, SPANID, null);
        assertNull(spanIdImpl2.getParentSpanId());
    }

    @Test
    public void testGetTraceId() {
        assertEquals(TRACEID, spanIdImpl.getTraceId());
    }

    @Test
    public void testGetSpanId() {
        assertEquals(SPANID, spanIdImpl.getSpanId());
    }

    @Test
    public void testGetParentSpanId() {
        assertEquals(PARENT_SPANID, spanIdImpl.getParentSpanId());
    }

    @Test
    public void testHashCode() {
        final SpanIdImpl equalSpanId = new SpanIdImpl(TRACEID, SPANID, PARENT_SPANID);
        assertEquals(spanIdImpl.hashCode(), equalSpanId.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(spanIdImpl.equals(spanIdImpl));
        assertFalse(spanIdImpl.equals(null));
        assertFalse(spanIdImpl.equals(new String()));

        final SpanIdImpl equalSpanId = new SpanIdImpl(TRACEID, SPANID, PARENT_SPANID);
        assertTrue(spanIdImpl.equals(equalSpanId));

        final SpanIdImpl nonEqualSpanId = new SpanIdImpl(TRACEID + 1, SPANID, PARENT_SPANID);
        final SpanIdImpl nonEqualSpanId2 = new SpanIdImpl(TRACEID, SPANID + 1, PARENT_SPANID);
        final SpanIdImpl nonEqualSpanId3 = new SpanIdImpl(TRACEID, SPANID, PARENT_SPANID + 1);

        assertFalse(spanIdImpl.equals(nonEqualSpanId));
        assertFalse(spanIdImpl.equals(nonEqualSpanId2));
        assertFalse(spanIdImpl.equals(nonEqualSpanId3));
    }

    @Test
    public void testToString() {
        assertEquals("[trace id: " + TRACEID + ", span id: " + SPANID + ", parent span id: " + PARENT_SPANID + "]",
            spanIdImpl.toString());
    }

    @Test
    public void testToStringNullParent() {
        final SpanIdImpl spanIdImpl2 = new SpanIdImpl(TRACEID, SPANID, null);
        assertEquals("[trace id: " + TRACEID + ", span id: " + SPANID + ", parent span id: null]", spanIdImpl2.toString());
    }

}
