<template>
  <div ref="container">
    <div ref="content">
      <a @click="addPage(-1)">&#10094;&nbsp;</a>
      <ion-button v-if="cropBefore" shape="round" size="small">
        ...
      </ion-button>
      <ion-button v-for="page in shownPages"
            shape="round"
            size="small"
            :class="buttonClass(page)"
            @click="currentPageIndex = page - 1"
      >
        {{ page }}
      </ion-button>
      <ion-button v-if="cropAfter" shape="round" size="small">
        ...
      </ion-button>
      <a @click="addPage(1)">&nbsp;&#10095;</a>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  props: {
    pages: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      currentPageIndex: 0,
      buttonData: {
        possibleButtons: 0
      }
    }
  },
  emits: ["pageChanged"],
  computed: {
    shownPages() {
      const pages: number[] = Array.from({length: 10}, (_, i) => i + 1);

      const listLength = pages.length;
      let startIndex = Math.max(0, this.currentPageIndex - 3);
      let endIndex = Math.min(listLength, this.currentPageIndex + 4);

      if (endIndex - startIndex < 7) {
        if (startIndex === 0) {
          endIndex = Math.min(listLength, startIndex + 7);
        } else {
          startIndex = Math.max(0, endIndex - 7);
        }
      }
      if (startIndex !== 0) {
        startIndex += 1;
      }
      if (endIndex !== this.pages) {
        endIndex -= 1;
      }

      return pages.slice(startIndex, endIndex);
    },
    cropBefore() {
      return this.shownPages[0] !== 1;
    },
    cropAfter() {
      return this.shownPages[this.shownPages.length - 1] !== this.pages;
    }
  },
  methods: {
    addPage(value: number) {
      let newPage = (this.currentPageIndex + value) % this.pages
      if (newPage < 0) {
        newPage = newPage + this.pages;
      }
      this.currentPageIndex = newPage;
    },
    buttonClass(index: number) {
      return { active: index === (this.currentPageIndex + 1) }
    }
  }
}
</script>

<style scoped lang="scss">
div {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  background-color: var(--ion-background-color-step-150);
}

a {
  font-size: 2rem;
  cursor: pointer;
  user-select: none;
}

ion-button {
  width: 1.8rem;
  height: 1rem;
  margin: 0.1rem;
  font-weight: bolder;
  color: white;

  &.active {
    --background: var(--ion-color-tertiary);
  }
}
</style>