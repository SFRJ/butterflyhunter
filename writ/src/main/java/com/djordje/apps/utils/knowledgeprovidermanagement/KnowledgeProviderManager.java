package com.djordje.apps.utils.knowledgeprovidermanagement;

import com.djordje.apps.model.KnowledgeProvider;

/**
 * Created with IntelliJ IDEA.
 * User: pro
 * Date: 21/12/13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public interface KnowledgeProviderManager {
    public void update(KnowledgeProvider knowledgeProvider);

    public KnowledgeProvider getKnowledgeProvider(String sfrj);
}
