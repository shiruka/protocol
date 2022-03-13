package io.github.shiruka.protocol.data.event;

/**
 * an enum class that contains agent results.
 */
public enum AgentResult {
  /**
   * the action fail.
   */
  ACTION_FAIL,
  /**
   * the action success.
   */
  ACTION_SUCCESS,
  /**
   * the query result false.
   */
  QUERY_RESULT_FALSE,
  /**
   * the query result true;
   */
  QUERY_RESULT_TRUE;

  /**
   * the values.
   */
  public static final AgentResult[] VALUES = AgentResult.values();
}
