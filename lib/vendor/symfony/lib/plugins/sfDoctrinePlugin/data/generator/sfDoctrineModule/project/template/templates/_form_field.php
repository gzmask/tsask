[?php if ($field->isPartial()): ?]
  [?php include_partial('<?php echo $this->getModuleName() ?>/'.$name, array('form' => $form, 'attributes' => $attributes instanceof sfOutputEscaper ? $attributes->getRawValue() : $attributes)) ?]
[?php elseif ($field->isComponent()): ?]
  [?php include_component('<?php echo $this->getModuleName() ?>', $name, array('form' => $form, 'attributes' => $attributes instanceof sfOutputEscaper ? $attributes->getRawValue() : $attributes)) ?]
[?php else: ?]
  <tr>
	<th width="20px"></th>		
	<td>
			<table>
			<tr>
				<td>[?php echo $form[$name]->render($attributes instanceof sfOutputEscaper ? $attributes->getRawValue() : $attributes) ?]
				</br>
				<span class="fonti">[?php echo $form[$name]->renderLabel($label) ?]</span>
				</td>
				<td>
				[?php if ($form[$name]->renderError()!=null): ?]
				<img src="/images/icon_ts.jpg" /></td><td>[?php echo $form[$name]->renderError() ?]</td>
				[?php endif; ?]
				  <div class="[?php echo $class ?][?php $form[$name]->hasError() and print ' errors' ?]">
					  [?php if ($help): ?]
						<div class="help">[?php echo __($help, array(), '<?php echo $this->getI18nCatalogue() ?>') ?]</div>
					  [?php elseif ($help = $form[$name]->renderHelp()): ?]
						<div class="help">[?php echo $help ?]</div>
					  [?php endif; ?]
				  </div>			
			</tr>
			</table>		
	</td>
  </tr>  
[?php endif; ?]
