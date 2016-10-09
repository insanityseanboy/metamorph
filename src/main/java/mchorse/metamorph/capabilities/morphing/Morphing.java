package mchorse.metamorph.capabilities.morphing;

import java.util.ArrayList;
import java.util.List;

import mchorse.metamorph.api.morph.Morph;
import mchorse.metamorph.api.morph.MorphManager;

/**
 * Default implementation of {@link IMorphing} interface.
 *
 * This class is responsible for storing current morphing, setting and retrieval
 * of current morphing.
 */
public class Morphing implements IMorphing
{
    private Morph morph;
    private String name = "";
    private List<String> acquiredMorphs = new ArrayList<String>();

    @Override
    public boolean acquireMorph(String name)
    {
        Morph morph = MorphManager.INSTANCE.morphs.get(name);

        if (morph == null || this.acquiredMorph(name))
        {
            return false;
        }

        this.acquiredMorphs.add(name);

        return true;
    }

    @Override
    public boolean acquiredMorph(String name)
    {
        return this.acquiredMorphs.contains(name);
    }

    @Override
    public List<String> getAcquiredMorphs()
    {
        return acquiredMorphs;
    }

    @Override
    public void setAcquiredMorphs(List<String> morphs)
    {
        this.acquiredMorphs.clear();
        this.acquiredMorphs.addAll(morphs);
    }

    @Override
    public Morph getCurrentMorph()
    {
        return this.morph;
    }

    @Override
    public String getCurrentMorphName()
    {
        return this.name;
    }

    @Override
    public void setCurrentMorph(String name, boolean creative)
    {
        if (creative || this.acquiredMorphs.contains(name))
        {
            this.morph = MorphManager.INSTANCE.morphs.get(name);
            this.name = name;
        }
    }

    @Override
    public void demorph()
    {
        this.morph = null;
        this.name = "";
    }

    @Override
    public boolean isMorphed()
    {
        return this.morph != null;
    }
}