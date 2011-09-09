package org.illarion.nifty.controls.chatbox;

import java.util.ArrayList;
import java.util.List;

/**
 * A disabled selection mode for a Nifty ListBox. You can't select anything.
 * @author void
 * @param <T>
 */
public class ListBoxSelectionModeDisabled<T> implements ListBoxSelectionMode<T> {
  private List<T> emptySelection = new ArrayList<T>();

  @Override
  public void clear() {
  }

  @Override
  public List<T> getSelection() {
    return emptySelection;
  }

  @Override
  public void remove(final T item) {
  }

  @Override
  public void removeForced(final T item) {
  }

  @Override
  public void add(final T item) {
  }

  @Override
  public void enableRequiresSelection(final boolean enable) {
  }

  @Override
  public boolean requiresAutoSelection() {
    return false;
  }
}
