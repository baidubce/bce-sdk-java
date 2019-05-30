package com.baidubce.services.cvca.model;

import java.util.List;
import java.util.Map;

import com.baidubce.model.AbstractBceResponse;

/**
 * chat response
 *
 * @author wujinlin
 */
public class ChatResponse extends AbstractBceResponse {
    private String answer;
    private String sessionId;
    private String endType;
    private String answerType;
    private NLU nlu;
    private KB kb;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public NLU getNlu() {
        return nlu;
    }

    public void setNlu(NLU nlu) {
        this.nlu = nlu;
    }

    public KB getKb() {
        return kb;
    }

    public void setKb(KB kb) {
        this.kb = kb;
    }

    public static class NLU {
        private String intent;
        private Map<String, Object> entities;

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public Map<String, Object> getEntities() {
            return entities;
        }

        public void setEntities(Map<String, Object> entities) {
            this.entities = entities;
        }
    }

    public static class KB {
        private List<String> similarQuery;

        public List<String> getSimilarQuery() {
            return similarQuery;
        }

        public void setSimilarQuery(List<String> similarQuery) {
            this.similarQuery = similarQuery;
        }
    }
}
