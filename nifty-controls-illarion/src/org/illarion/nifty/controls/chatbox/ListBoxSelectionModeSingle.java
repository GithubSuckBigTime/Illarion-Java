package org.illarion.nifty.controls.chatbox;

import java.util.ArrayList;
import java.util.List;

/**
 * A single selection mode for a Nifty ListBox. You can only select a single item.
 * Selecting a new one will remove any previous selected items.
 * @author void
 * @param <T>
 */
public class ListBoxSelectionModeSingle<T> implements ListBoxSelectionMode<T> {
  private List<T> selection = new ArrayList<T>();
  private boolean requiresSelection = false;

  @Override
  public void clear() {
    selection.clear();
  }

  @Override
  public List<T> getSelection() {
    return new ArrayList<T>(selection);
  }

  @Override
  public void remove(final T item) {
    if (requiresSelection) {
      return;
    }
    if (isPartOfSelection(item)) {
      removeFromSelection(item);
    }
  }

  @Override
  public void removeForced(final T item) {
    if (isPartOfSelection(item)) {
      removeFromSelection(item);
    }
  }

  @Override
  public void add(final T item) {
    selection.clear();
    selection.add(item);
  }

  @Override
  public void enableRequiresSelection(final boolean enable) {
    requiresSelection = enable;
  }

  @Override
  public boolean requiresAutoSelection() {
    return requiresSelection && selection.isEmpty();
  }

  private boolean isPartOfSelection(final T item) {
    return selection.contains(item);
  }

  private void removeFromSelection(final T item) {
    selection.remove(item);
  }
}
